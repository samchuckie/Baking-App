package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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

public class MainActivity extends AppCompatActivity implements AllRecipesAdapter.ItemClickInterface {
    // TODO : CHECK INTERNET CONNCETIVITY FIRST
    RecyclerView allRecipes;
    LinearLayoutManager linearLayoutManager;
    AllRecipesAdapter allRecipesAdapter;
    RecipesInt recipesInt;
    DividerItemDecoration decoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allRecipes = findViewById(R.id.allitems);
        linearLayoutManager = new LinearLayoutManager(this);
        decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        allRecipes.addItemDecoration(decoration);
        allRecipes.setLayoutManager(linearLayoutManager);
        allRecipesAdapter = new AllRecipesAdapter(this);
        allRecipes.setAdapter(allRecipesAdapter);
        recipesInt = RecipeRetro.getinsance().create(RecipesInt.class);
        Call<List<Recipe>> recipesCall = recipesInt.getall();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> lrecipe=response.body();;
                //final Recipes s = new Recipes(lrecipe);
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
}
