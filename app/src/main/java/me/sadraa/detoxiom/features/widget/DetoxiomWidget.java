package me.sadraa.detoxiom.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.RemoteViews;

import me.sadraa.detoxiom.R;
import me.sadraa.detoxiom.features.main_activity.MainActivity;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link DetoxiomWidgetConfigureActivity DetoxiomWidgetConfigureActivity}
 */
public class DetoxiomWidget extends AppWidgetProvider {

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //Load widget from prefrence that save on conf activity
        String packageName = DetoxiomWidgetConfigureActivity.loadPref(context, appWidgetId);
        //load app logo
        InstalledAppsLoader installedAppsLoader = new InstalledAppsLoader(context);
        Uri appLogo = null;
        try {
            appLogo = installedAppsLoader.getAppUriIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //load app label
        String appLabel = installedAppsLoader.getAppLabel(packageName);

        //Creating remote view from widget layout
        RemoteViews views = new RemoteViews("me.sadraa.detoxiom", R.layout.detoxiom_widget);
        //set text view
        views.setTextViewText(R.id.widgetText, appLabel);
        //set image view

        //Ok, ladies and Gentlemen there is a wired thing. All toturial across the Internet says we should write
        //below codes in onUpdate method. I did it but it didn't work. It bother me badly because I just struggle with
        //It for 2 days. finally I understand the problem. apparently If you use conf activity for creating a widget
        // you should update it here not in onUpdate method.
        if (appLogo != null)
            views.setImageViewUri(R.id.ImageButton, appLogo);

        Intent mIntent = new Intent(context, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context, 1, mIntent, 0);

        views.setOnClickPendingIntent(R.id.ImageButton, pIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

        }


    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            DetoxiomWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

    }
}

