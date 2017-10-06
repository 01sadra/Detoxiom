package me.sadraa.detoxiom;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * The configuration screen for the {@link DetoxiomWidget DetoxioWidget} AppWidget.
 */
public class DetoxiomWidgetConfigureActivity extends AppCompatActivity {
    PackageManager pm;
    List<ApplicationInfo> listOfAppInfo;
    ArrayList<String> nameOfAppsArray = new ArrayList<String>();
    ArrayList<Drawable> appLogosArray = new ArrayList<Drawable>();
    ListView listView;
    public DetoxiomWidgetConfigureActivity() {
        super();
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.detoxiom_widget_configure);
        //set Toolbar
        Toolbar confToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(confToolbar);
        //setting logo and title for app dynamicly
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Pick app you want");
        getSupportActionBar().setSubtitle("hello");

        createArrayFromApps();
        listView = (ListView) findViewById(R.id.list_view_conf_activity);
        listView.setAdapter(new ListViewCostumAdapter(this,nameOfAppsArray,appLogosArray));

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

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
}

