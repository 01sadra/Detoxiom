package me.sadraa.detoxiom.features.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Single;

import static me.sadraa.detoxiom.features.widget.App.getUriFromResourceId;


public class InstalledAppsLoader {

    private PackageManager packageManager;
    private Context context;

    public InstalledAppsLoader(Context context) {
        this.context = context;
        packageManager = context.getPackageManager();
    }


    public Single<List<App>> getAppsObservable() {
        return Single.create(emitter -> {
            try {
                List<ApplicationInfo> apps =
                        packageManager.getInstalledApplications(0);


                // create corresponding apps and load their labels
                List<App> items = new ArrayList<>();
                for (int i = 0; i < apps.size(); i++) {
                    String packageName = apps.get(i).packageName;

                    // only apps which are launchable
                    if (context.getPackageManager()
                            .getLaunchIntentForPackage(packageName) != null) {

                        App app = new App(context, apps.get(i));
                        app.loadLabel(context);
                        items.add(app);
                    }
                }

                // sort the list
                Collections.sort(items, ALPHA_COMPARATOR);
                emitter.onSuccess(items);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


    public Drawable getAppDrawableIcon(String packageName) {
        try {
            return packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAppLabel(String packageName) {
        ApplicationInfo ai;
        try {
            ai = packageManager.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        return (String) (ai != null ? packageManager.getApplicationLabel(ai) : "(unknown)");
    }

    public Uri getAppUriIcon(String packageName) throws PackageManager.NameNotFoundException {
        return getUriFromResourceId(packageName, packageManager.getApplicationInfo(packageName, 0).icon);
    }

    public Bitmap getAppBitmapIcon(String packageName) {
        Drawable drawable = getAppDrawableIcon(packageName);
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Perform alphabetical comparison of application entry objects.
     */
    private static final Comparator<App> ALPHA_COMPARATOR = new Comparator<App>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(App object1, App object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };

}
