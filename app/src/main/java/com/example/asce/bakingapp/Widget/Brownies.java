package com.example.asce.bakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Services.RecipeServices;
import com.example.asce.bakingapp.Services.WidgetService;
import static com.example.asce.bakingapp.Constant.Const.APPWIDGET_ID;
import static com.example.asce.bakingapp.Constant.Const.ITEM;

/**
 * Implementation of App Widget functionality.
 */
public class Brownies extends AppWidgetProvider {
    private static final String RECIPENAME ="Brownies";
    private static int appwidgid;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
       // views.setTextViewText(R.id.recipe_title , RECIPENAME);
        Intent data = new Intent(context,WidgetService.class) ;
        data.setAction(RECIPENAME);
        views.setRemoteAdapter(R.id.ingredient_list , data);
        views.setEmptyView(R.id.ingredient_list , R.id.empty_tv);
        Intent clickedintent =new Intent(context ,Brownies.class);
        clickedintent.setAction("Brownies");
        clickedintent.putExtra(APPWIDGET_ID ,appWidgetId);
        appwidgid=appWidgetId;
        PendingIntent clickedPendingIntent = PendingIntent.getBroadcast(context,
                0, clickedintent, 0);
        views.setPendingIntentTemplate(R.id.ingredient_list, clickedPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

  
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (("Brownies").equals(intent.getAction())){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_layout);
            Intent data = new Intent(context,RecipeServices.class) ;
            data.setAction(intent.getStringExtra(ITEM));
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

