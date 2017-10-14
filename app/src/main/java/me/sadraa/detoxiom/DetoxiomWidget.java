package me.sadraa.detoxiom;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link DetoxiomWidgetConfigureActivity DetoxiomWidgetConfigureActivity}
 */
public class DetoxiomWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //Load widget from prefrence that save on conf activity
       int position = DetoxiomWidgetConfigureActivity.loadPref(context,appWidgetId);
        //load app logo
       Bitmap appLogoBitmap = DetoxiomWidgetConfigureActivity.loadAppLogo(position);
        //load app label
       String appLabel = DetoxiomWidgetConfigureActivity.loadAppLabel(position);

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
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.detoxiom_widget);

            //make pending intent that wake mainActivity
            Intent mIntent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,mIntent,0);
            views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
           // ComponentName myWidget = new ComponentName( context, DetoxiomWidget.class );

            //appWidgetManager.updateAppWidget( myWidget, views);

            appWidgetManager.updateAppWidget(appWidgetId,views);

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
}

