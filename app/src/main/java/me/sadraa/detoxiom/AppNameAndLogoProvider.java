package me.sadraa.detoxiom;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadra on 11/10/17.
 */

public class AppNameAndLogoProvider {
    Context context;
    List<ApplicationInfo> listOfAppInfo;

    //Always initiate arraylists
    private ArrayList<String> nameOfAppsArray = new ArrayList<>();
    private ArrayList<Drawable> appLogosArray = new ArrayList<>();

    public AppNameAndLogoProvider(Context context) {
        this.context = context;
    }

    //Create two arrayList from name and logos of installed apps

    public ArrayList<String> getNameOfAppsArray() {
        PackageManager pm = context.getPackageManager();
        listOfAppInfo = pm.getInstalledApplications(pm.GET_META_DATA);
        for(ApplicationInfo app : listOfAppInfo) {

            if(pm.getLaunchIntentForPackage(app.packageName) != null) {
                // apps with launcher intent
                if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
                    // updated system apps

                } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    // system apps

                } else {
                    // user installed apps
                    nameOfAppsArray.add((String) pm.getApplicationLabel(app));
                }
            }
        }
        return nameOfAppsArray;
    }

    public ArrayList<Drawable> getAppLogosArray() {
        PackageManager pm = context.getPackageManager();
        listOfAppInfo = pm.getInstalledApplications(pm.GET_META_DATA);

        for(ApplicationInfo app : listOfAppInfo) {

            if(pm.getLaunchIntentForPackage(app.packageName) != null) {
                // apps with launcher intent
                if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
                    // updated system apps

                } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    // system apps

                } else {
                    // user installed apps
                    appLogosArray.add(app.loadIcon(pm));

                }
            }

        }
        return appLogosArray;
    }


    public Bitmap loadAppLogo(int position){
        appLogosArray = getAppLogosArray();
        Drawable appLogo = appLogosArray.get(position);
        //convert Drawble to bitmap
        Bitmap appLogoBitmap = ((BitmapDrawable)appLogo).getBitmap();
        return appLogoBitmap;
    }

    public String loadAppLabel(int position){
        nameOfAppsArray = getNameOfAppsArray();
        String appLabel = nameOfAppsArray.get(position);
        return appLabel;
    }

}
