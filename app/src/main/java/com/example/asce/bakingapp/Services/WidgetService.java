package com.example.asce.bakingapp.Services;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.asce.bakingapp.Ingredient;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Recipe;
import com.example.asce.bakingapp.Retro.RecipeRetro;
import com.example.asce.bakingapp.Retro.RecipesInt;
import com.example.asce.bakingapp.Widget.Brownies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.asce.bakingapp.Constant.Const.APPWIDGET_ID;
import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEY;
import static com.example.asce.bakingapp.Constant.Const.ITEM;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListsFactory(this.getApplicationContext(),intent);
    }
    class ListsFactory implements RemoteViewsService.RemoteViewsFactory{
        private Context mcontext;
        private Intent mintent;
        private List<Recipe> lrecipe;
        public ListsFactory(Context applicationContext, Intent intent) {
            mcontext = applicationContext;
            mintent=intent;
        }
        @Override
        public void onCreate() {
            RecipesInt recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
            Call<List<Recipe>> recipesCall = recipesInt.getall();
            recipesCall.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    lrecipe = response.body();
                    Log.e("sam" , "widgetter");
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                }
            });
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lrecipe!=null){
                return lrecipe.size();
            }
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mcontext.getPackageName() ,R.layout.widgetlist_layout);
            String recipename = lrecipe.get(position).getName();
            remoteViews.setTextViewText(R.id.widgetlist_item , recipename);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(ITEM, recipename);
            remoteViews.setOnClickFillInIntent(R.id.widgetlist_item, fillInIntent);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
