package me.sadraa.detoxiom;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * The configuration screen for the {@link DetoxiomWidget DetoxioWidget} AppWidget.
 */
public class DetoxiomWidgetConfigureActivity extends AppCompatActivity {
    PackageManager pm;
    List<ApplicationInfo> listOfAppInfo;
    //Always initiate arraylists
    public  ArrayList<String> nameOfAppsArray = new ArrayList<String>();
    public  ArrayList<Drawable> appLogosArray = new ArrayList<Drawable>();
    AppNameAndLogoProvider appNameAndLogoProvider;
    ListView listView;

    //Name and keyname for SharedPrefrences
    private static final String PREFS_NAME = "me.sadraa.Detoxim.DetoxiomWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    //Set App widgetId = invalid by default
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
        Toolbar confToolbar = (Toolbar) findViewById(R.id.toolbar_widget_conf);
        setSupportActionBar(confToolbar);

        //setting logo and title for app dynamically
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Detoxiom");
        getSupportActionBar().setSubtitle("Choose the App you want");
        //Call function that make Array of logo and labels of installed app

        appNameAndLogoProvider = new AppNameAndLogoProvider(this);

        nameOfAppsArray = appNameAndLogoProvider.getNameOfAppsArray();
        appLogosArray = appNameAndLogoProvider.getAppLogosArray();

        //set adapter for listView
        listView = (ListView) findViewById(R.id.list_view_conf_activity);
        listView.setAdapter(new ListViewCostumAdapter(this,nameOfAppsArray,appLogosArray));
        //set listview clackable
        listView.setClickable(true);
        final DetoxiomWidget detoxiomWidget=new DetoxiomWidget();
        //define listener for listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                //save posotion and app widget id in prefrence
                savePref(context, mAppWidgetId,position);
                //update app widget
                detoxiomWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
                //add app widget id to intent
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                //finish activity
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



    //saving prefrence
    public void savePref(Context context, int appWidgetId, int position) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putInt(PREF_PREFIX_KEY + appWidgetId, position);
        prefs.apply();
    }
    //static method that can be called from other classes
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


}

