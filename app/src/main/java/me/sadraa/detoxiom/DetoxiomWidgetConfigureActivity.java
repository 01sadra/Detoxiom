package me.sadraa.detoxiom;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * The configuration screen for the {@link DetoxiomWidget DetoxioWidget} AppWidget.
 */
public class DetoxiomWidgetConfigureActivity extends AppCompatActivity {
    PackageManager pm;
    List<ApplicationInfo> listOfAppInfo;
    //Always initiate arraylists
    public static ArrayList<String> nameOfAppsArray = new ArrayList<String>();
    public static ArrayList<Drawable> appLogosArray = new ArrayList<Drawable>();

    ListView listView;

    private static final String PREFS_NAME = "me.sadraa.Detoxim.DetoxiomWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";

    final static String mPrefKey = "preKey";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    final Context context = DetoxiomWidgetConfigureActivity.this;

    public DetoxiomWidgetConfigureActivity() {

        super();
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.detoxiom_widget_configure);
        setResult(RESULT_CANCELED);


        //set Toolbar
        Toolbar confToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(confToolbar);
        //setting logo and title for app dynamicly

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Detoxiom");
        getSupportActionBar().setSubtitle("Choose the App you want");

        createArrayFromApps();

        listView = (ListView) findViewById(R.id.list_view_conf_activity);
        listView.setAdapter(new ListViewCostumAdapter(this,nameOfAppsArray,appLogosArray));
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                savePref(context, mAppWidgetId,position);

                DetoxiomWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();

            }
        });



        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
    }

    public void createArrayFromApps(){
        //Create two arrayList from name and logos of installed apps

        pm = getPackageManager();
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

                    appLogosArray.add(app.loadIcon(pm));

                }
            }

            // pm.getApplicationLogo(m)

        }
    }

    public void savePref(Context context, int appWidgetId, int position) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putInt(PREF_PREFIX_KEY + appWidgetId, position);
        prefs.apply();
    }

    static int loadPref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        int position = prefs.getInt(PREF_PREFIX_KEY + appWidgetId, 0);
            return position;

    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    static Bitmap loadAppLogo(int position){

            Drawable appLogo = appLogosArray.get(position);
            Bitmap appLogoBitmap = ((BitmapDrawable)appLogo).getBitmap();
            return appLogoBitmap;



    }
    static String loadAppLabel(int position){
        String appLabel = nameOfAppsArray.get(position);
        return appLabel;
    }
}

