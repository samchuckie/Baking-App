package com.example.asce.bakingapp.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.asce.bakingapp.Ingredient;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Recipe;
import com.example.asce.bakingapp.Retro.RecipeRetro;
import com.example.asce.bakingapp.Retro.RecipesInt;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeServices extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListFactory(this.getApplicationContext(),intent);
    }
}
class ListFactory implements RemoteViewsService.RemoteViewsFactory{
    List<Recipe> lrecipe;
    List<Ingredient> ingrident;
    List<String> ingridentText = new ArrayList<>();
    private Context mcontext;
    Intent mintent;
    String action;
    public ListFactory(Context applicationContext, Intent intent) {
        mcontext = applicationContext;
        mintent=intent;
    }
    @Override
    public void onCreate() {
        action = mintent.getAction();
        RecipesInt recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
        Call<List<Recipe>> recipesCall = recipesInt.getall();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                lrecipe=response.body();
                for(int counter = 0 ; counter <lrecipe.size();counter++) {
                    if (lrecipe.get(counter).getName().equals(action)){
                        ingrident = lrecipe.get(counter).getIngredients();
                    }
                }
                for(int count = 0 ; count <ingrident.size();count++){
                    ingridentText.add(String.valueOf(count) + ". " + ingrident.get(count).getIngredient());
                }
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
        if (ingridentText!=null){
            return ingridentText.size();
        }
        return 0;
    }
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mcontext.getPackageName() ,R.layout.widgetlist_layout);
        remoteViews.setTextViewText(R.id.widgetlist_item , ingridentText.get(position));
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
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}