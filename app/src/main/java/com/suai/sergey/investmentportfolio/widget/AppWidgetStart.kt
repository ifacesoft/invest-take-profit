package com.suai.sergey.investmentportfolio.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.suai.sergey.investmentportfolio.MainActivity
import com.suai.sergey.investmentportfolio.R

/**
 * Implementation of App Widget functionality.
 */
class AppWidgetStart : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val pendingIntent:PendingIntent = Intent(context, MainActivity::class.java).let { intent -> PendingIntent.getActivity(context, 0, intent, 0) }

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.app_widget_start).apply {
                setOnClickPendingIntent(R.id.widgetButton, pendingIntent)
            }
            views.setTextViewText(R.id.widgetButton, "1000")
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

