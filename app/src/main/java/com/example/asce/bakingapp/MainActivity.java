package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.asce.bakingapp.Adapters.AllRecipesAdapter;
import com.example.asce.bakingapp.Retro.RecipeRetro;
import com.example.asce.bakingapp.Retro.RecipesInt;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AllRecipesAdapter.ItemClickInterface,RecipeResources.TimeInt {
    // TODO : CHECK INTERNET CONNCETIVITY FIRST
    RecyclerView allRecipes;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    AllRecipesAdapter allRecipesAdapter;
    RecipesInt recipesInt;
    Boolean portait;
    IdlingResourceEx idlingResourceEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.allitems) !=null){
            portait = true;
            allRecipes = findViewById(R.id.allitems);
            linearLayoutManager = new LinearLayoutManager(this);
            allRecipes.setLayoutManager(linearLayoutManager);
            // todo add touch selector
        }
        else {
            Log.e("sam" , "Its landscape mode");
            allRecipes = findViewById(R.id.allitems_land);
            gridLayoutManager = new GridLayoutManager(this ,2);
            allRecipes.setLayoutManager(gridLayoutManager);
        }
        allRecipesAdapter = new AllRecipesAdapter(this ,this);
        allRecipes.setAdapter(allRecipesAdapter);
        recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
        Call<List<Recipe>> recipesCall = recipesInt.getall();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> lrecipe=response.body();
                allRecipesAdapter.setRecipes(lrecipe);
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
            }
        });
    }

    @Override
    public void itemClick(Recipe r) {
        Intent intent= new Intent(this , SpecificRecipe.class);
        // TODO make name a constant
        intent.putExtra("a",r);
        startActivity(intent);
    }

    @Override
    public void LoadComplete(List<Recipe> lrecipe) {
        allRecipesAdapter.setRecipes(lrecipe);
    }
    @VisibleForTesting
    public IdlingResourceEx getIdlingresource() {
        if (idlingResourceEx == null) {
            Log.e("sam" , "Idling resource set");
            idlingResourceEx = new IdlingResourceEx();
        }
        return  idlingResourceEx;
    }
    @VisibleForTesting
    public void callNetwork(){
        RecipeResources.getRecipes(this,idlingResourceEx);
    }
}
