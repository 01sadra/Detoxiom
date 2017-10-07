package me.sadraa.detoxiom;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
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

       int position = DetoxiomWidgetConfigureActivity.loadPref(context,appWidgetId);

       Bitmap appLogoBitmap = DetoxiomWidgetConfigureActivity.loadAppLogo(position);

       String appLabel = DetoxiomWidgetConfigureActivity.loadAppLabel(position);


        RemoteViews views = new RemoteViews("me.sadraa.detoxiom", R.layout.detoxiom_widget);

       views.setTextViewText(R.id.widgetText, appLabel);


        views.setImageViewBitmap(R.id.widgetImage,appLogoBitmap);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

