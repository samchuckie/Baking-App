package com.example.asce.bakingapp.Widget;

import android.app.PendingIntent;
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

import static com.example.asce.bakingapp.Constant.Const.APPWIDGET_ID;
import static com.example.asce.bakingapp.Constant.Const.ITEM;

/**
 * Implementation of App Widget functionality.
 */
public class Brownies extends AppWidgetProvider {
    private static final String RECIPENAME ="Brownies";
    private static int appwidgid;
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
        Intent clickedintent =new Intent(context ,Brownies.class);
        clickedintent.setAction("Brownies");
        clickedintent.putExtra(APPWIDGET_ID ,appWidgetId);
        appwidgid=appWidgetId;
        Log.e("sam " , "Appwidget id is " + appWidgetId);
        Log.e("sam " , "Appwidget static id is " + appwidgid);
        PendingIntent clickedPendingIntent = PendingIntent.getBroadcast(context,
                0, clickedintent, 0);
        views.setPendingIntentTemplate(R.id.ingredient_list, clickedPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

  
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

//    static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
//        Intent data = new Intent(context,RecipeServices.class) ;
//        data.setAction(intent.getStringExtra(ITEM));
//        Log.e("sam " , "AppwidgetSS id is " + intent.getIntExtra(APPWIDGET_ID,0));
//        Log.e("sam " , "AppwidgetSS action is " + intent.getAction());
//        views.setRemoteAdapter(R.id.ingredient_list , data);
//        views.setEmptyView(R.id.ingredient_list , R.id.empty_tv);
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (("Brownies").equals(intent.getAction())){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
            Intent data = new Intent(context,RecipeServices.class) ;
            data.setAction(intent.getStringExtra(ITEM));
            Log.e("sam " , "AppwidgetSS id is " + intent.getIntExtra(APPWIDGET_ID,appwidgid));
            Log.e("sam " , "AppwidgetSS action is " + intent.getAction());
            views.setRemoteAdapter(R.id.ingredient_list , data);
            views.setEmptyView(R.id.ingredient_list , R.id.empty_tv);
            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntExtra(APPWIDGET_ID,appwidgid), views);

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

