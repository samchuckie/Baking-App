package com.example.asce.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Services.RecipeServices;

/**
 * Implementation of App Widget functionality.
 */
public class Brownies extends AppWidgetProvider {
    private static final String RECIPENAME ="Brownies";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
        views.setTextViewText(R.id.recipe_title , RECIPENAME);
        Intent data = new Intent(context,RecipeServices.class) ;
        data.setAction(RECIPENAME);
        views.setRemoteAdapter(R.id.ingredient_list , data);
        views.setEmptyView(R.id.ingredient_list , R.id.empty_tv);
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
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

