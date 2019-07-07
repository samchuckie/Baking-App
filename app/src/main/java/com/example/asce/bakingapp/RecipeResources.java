package com.example.asce.bakingapp;

import android.support.annotation.Nullable;

import com.example.asce.bakingapp.Model.Recipe;
import com.example.asce.bakingapp.Retro.RecipeRetro;
import com.example.asce.bakingapp.Retro.RecipesInt;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RecipeResources {
    interface TimeInt{
        void LoadComplete(List<Recipe> lrecipe);
    }
    static void getRecipes(final TimeInt timeInt,@Nullable final IdlingResourceEx idlingResourceEx) {
        if (idlingResourceEx != null) {
            idlingResourceEx.setIdleState(false);
            RecipesInt recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
            Call<List<Recipe>> recipesCall = recipesInt.getall();
            recipesCall.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    List<Recipe> lrecipe = response.body();
                    timeInt.LoadComplete(lrecipe);
                    idlingResourceEx.setIdleState(true);
                }
                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                }
            });
        }
    }
}
