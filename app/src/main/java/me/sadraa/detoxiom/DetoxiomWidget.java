package me.sadraa.detoxiom;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link DetoxiomWidgetConfigureActivity DetoxiomWidgetConfigureActivity}
 */
public class DetoxiomWidget extends AppWidgetProvider {
        AppNameAndLogoProvider appNameAndLogoProvider;

        public  void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                           int appWidgetId) {

            appNameAndLogoProvider = new AppNameAndLogoProvider(context);
        //Load widget from prefrence that save on conf activity
       int position = DetoxiomWidgetConfigureActivity.loadPref(context,appWidgetId);
        //load app logo
       Bitmap appLogoBitmap = appNameAndLogoProvider.loadAppLogo(position);
        //load app label
       String appLabel = appNameAndLogoProvider.loadAppLabel(position);

        //Creating remote view from widget layout
        RemoteViews views = new RemoteViews("me.sadraa.detoxiom", R.layout.detoxiom_widget);
        //set text view
        views.setTextViewText(R.id.widgetText, appLabel);
        //set image view
        views.setImageViewBitmap(R.id.widgetImage,appLogoBitmap);

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

