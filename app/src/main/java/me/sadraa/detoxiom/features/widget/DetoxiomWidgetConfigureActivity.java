package me.sadraa.detoxiom.features.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.widget.adapter.ListViewCostumAdapter;

/**
 * The configuration screen for the {@link DetoxiomWidget DetoxioWidget} AppWidget.
 */
public class DetoxiomWidgetConfigureActivity extends AppCompatActivity {

    Disposable appsDisposable;

    @BindView(R.id.list_view_conf_activity)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.toolbar_widget_conf)
    Toolbar confToolbar;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    //Name and keyname for SharedPrefrences
    private static final String PREFS_NAME = "me.sadraa.Detoxim.DetoxiomWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    //Set App widgetId = invalid by default
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    final Context context = DetoxiomWidgetConfigureActivity.this;

    public DetoxiomWidgetConfigureActivity() {
        super();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.detoxiom_widget_configure);
        ButterKnife.bind(this);
        setResult(RESULT_CANCELED);
        progressBar.setVisibility(View.VISIBLE);
        //set Toolbar
        setSupportActionBar(confToolbar);

        //setting logo and title for app dynamically
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setSubtitle("اپلیکیشن مورد نظر خودتون رو انتخاب کنید");
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        InstalledAppsLoader installedAppsLoader =
                new InstalledAppsLoader(this);
        appsDisposable = installedAppsLoader.getAppsObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(appsArray -> {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    setupRecycler(appsArray);
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

    void setupRecycler(List<App> apps) {

        recyclerView.setLayoutManager(new GridLayoutManager(this,
                3));

        ListViewCostumAdapter listViewCostumAdapter =
                new ListViewCostumAdapter(this, apps);
        //set adapter for listView
        recyclerView.setAdapter(listViewCostumAdapter);
        //set listview clackable
        recyclerView.setClickable(true);
        final DetoxiomWidget detoxiomWidget = new DetoxiomWidget();
        //define listener for listview

        listViewCostumAdapter.setClickListener((appModel, position) -> {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            //save posotion and app widget id in prefrence
            savePref(context, mAppWidgetId, appModel.getPackageName());
            //update app widget
            detoxiomWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
            //add app widget id to intent
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            //finish activity
            finish();
        });
    }


    //saving prefrence
    public void savePref(Context context, int appWidgetId, String packageName) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, packageName);
        prefs.apply();
    }

    //static method that can be called from other classes
    @Deprecated
    public static String loadPref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(PREF_PREFIX_KEY + appWidgetId, context.getPackageName());
    }

    public static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appsDisposable.dispose();
    }
}

