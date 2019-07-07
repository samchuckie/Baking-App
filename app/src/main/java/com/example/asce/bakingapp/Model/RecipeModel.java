package com.example.asce.bakingapp.Model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.asce.bakingapp.Retro.RecipeRetro;
import com.example.asce.bakingapp.Retro.RecipesInt;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeModel {
    private MutableLiveData<List<Recipe>> mutableRecipe =  new MutableLiveData<>();
    public MutableLiveData<List<Recipe>> getMutableRecipe() {
        return mutableRecipe;
    }

    public  RecipeModel(){
        RecipesInt recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
        Call<List<Recipe>> recipesCall = recipesInt.getall();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.e("sam" ,"Call from model");
                mutableRecipe.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
            }
        });
    }


}
