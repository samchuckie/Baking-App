package com.example.asce.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.asce.bakingapp.Ingredient;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Services.RecipeServices;
import com.example.asce.bakingapp.Services.WidgetService;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class Brownies extends AppWidgetProvider {
    private static final String RECIPENAME ="Brownies";
    //TODO CHANGING THIS CLASS
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
       // views.setTextViewText(R.id.recipe_title , RECIPENAME);
        Intent data = new Intent(context,WidgetService.class) ;
        data.setAction(RECIPENAME);
        Log.e("sam" ,"onfirst update");
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
    public static void updateList(Context context, AppWidgetManager appWidgetManager, String ingredients, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager,ingredients, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String ingredients, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
        views.setTextViewText(R.id.recipe_title , RECIPENAME);
        Intent data = new Intent(context,WidgetService.class) ;
        data.setAction(RECIPENAME);
        Log.e("sam" ,"second update is called" + ingredients);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("Brownies")){
            Log.e("sam" ,"broww");

        }
        else {
            Log.e("sam" ,"others");
        }
        super.onReceive(context, intent);
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

