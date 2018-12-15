package com.example.asce.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Services.RecipeServices;

public class NutellaPie extends AppWidgetProvider {
    private static final String RECIPENAME ="Nutella Pie";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
        views.setTextViewText(R.id.recipe_title , RECIPENAME);
        Intent data = new Intent(context,RecipeServices.class) ;
        data.setAction(RECIPENAME);
        views.setRemoteAdapter(R.id.ingredient_list , data);
        views.setEmptyView(R.id.ingredient_list , R.id.empty_tv);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

