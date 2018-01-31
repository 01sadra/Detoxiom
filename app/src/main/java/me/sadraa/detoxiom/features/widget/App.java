package me.sadraa.detoxiom.features.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;


/**
 * Created by alinasrabadi on 01/30/18.
 */

public final class App {

    private final Context mContext;
    private final ApplicationInfo mInfo;

    private String mAppLabel;
    private Drawable mIcon;

    private boolean mMounted;
    private final File mApkFile;

    PackageInfo mPackageInfo;

    public App(Context context, ApplicationInfo info) {
        mContext = context;
        mInfo = info;
        try {
            mPackageInfo = context.getPackageManager()
                    .getPackageInfo(info.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mApkFile = new File(info.sourceDir);
    }

    public ApplicationInfo getAppInfo() {
        return mInfo;
    }

    public String getApplicationPackageName() {
        return getAppInfo().packageName;
    }

    public String getLabel() {
        return mAppLabel;
    }

    public File getApkFile() {
        return mApkFile;
    }

    public Drawable getIcon() {
        if (mIcon == null) {
            if (mApkFile.exists()) {
                mIcon = mInfo.loadIcon(mContext.getPackageManager());
                return mIcon;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload
            // its icon.
            if (mApkFile.exists()) {
                mMounted = true;
                mIcon = mInfo.loadIcon(mContext.getPackageManager());
                return mIcon;
            }
        } else {
            return mIcon;
        }

        return mContext.getResources().getDrawable(android.R.drawable.sym_def_app_icon);
    }

    public Uri getIconURI() {
        return getUriFromResourceId(getPackageName(), getAppInfo().icon);
    }


    public String getPackageName() {
        return mInfo.packageName;
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    public PackageInfo getPackageInfo(String packageName) throws PackageManager.NameNotFoundException {
        return mContext.getPackageManager()
                .getPackageInfo(packageName, 0);
    }

    public boolean isSystemApp() {
        return (mInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    @Nullable
    public ResolveInfo getResolverInfo() {

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = mContext.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (ResolveInfo info : resolveInfos) {
            if (mInfo.packageName.equals(info.activityInfo.applicationInfo.packageName))
                return info;
        }

        return null;
    }


    public void loadLabel(Context context) {
        if (mAppLabel == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mAppLabel = mInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mInfo.loadLabel(context.getPackageManager());
                mAppLabel = label != null ? label.toString() : mInfo.packageName;
            }
        }
    }

    private static final String ANDROID_RESOURCE = "android.resource://";
    private static final String FORWARD_SLASH = "/";

    public static Uri getUriFromResourceId(String packageName, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + packageName + FORWARD_SLASH + resourceId);
    }

}
