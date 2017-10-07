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
        DetoxiomWidgetConfigureActivity dt = new DetoxiomWidgetConfigureActivity();

        int position = dt.loadPref();
//        Bitmap appLogoBitmap = dt.loadAppLogo(position);
//        String appLabel = dt.loadAppLabel(position);


        RemoteViews views = new RemoteViews("me.sadraa.detoxiom", R.layout.detoxiom_widget);
 //       views.setTextViewText(R.id.widgetText, appLabel);
        views.setTextViewText(R.id.widgetText, "salam");


  //      views.setImageViewBitmap(R.id.widgetImage,appLogoBitmap);

        views.setImageViewResource(R.id.widgetImage,R.mipmap.ic_launcher);

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

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

