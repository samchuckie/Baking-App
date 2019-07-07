package com.example.asce.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.asce.bakingapp.Adapters.AllRecipesAdapter;
import com.example.asce.bakingapp.Model.Recipe;
import com.example.asce.bakingapp.ViewModel.BakingViewModel;
import java.util.List;
import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;

public class HomeActivity extends AppCompatActivity implements AllRecipesAdapter.ItemClickInterface,RecipeResources.TimeInt {
    private AllRecipesAdapter allRecipesAdapter;
    private IdlingResourceEx idlingResourceEx;

    //TODO CHANGE FONT - IMPORTANT
    //TODO ADD TOUCH SELECTOR - IMPORTANT
    //TODO PUT INGREDIENTS AND STEPS ICON
    //TODO PUT DIMENS IN FOLDER
    //TODO CHANGE DIMES OF TABLETS
    //TODO apply back navigation and tell the user which recipe they are on
    //TODO MIGRATE TO STAGGERED VIEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView allRecipes;
        BakingViewModel bakingViewModel = ViewModelProviders.of(this).get(BakingViewModel.class);
        if(findViewById(R.id.all_items) !=null){
            allRecipes = findViewById(R.id.all_items);
        }
        else {
            allRecipes = findViewById(R.id.allitems_land);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        allRecipes.setLayoutManager(gridLayoutManager);
        allRecipesAdapter = new AllRecipesAdapter(this ,this);
        allRecipes.setAdapter(allRecipesAdapter);
        bakingViewModel.getRecipeList().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                allRecipesAdapter.setRecipes(recipes);
            }
        });
    }
    @Override
    public void itemClick(Recipe r) {
        Intent intent= new Intent(this , SpecificRecipe.class);
        intent.putExtra(MAIN_ACTIVITY,r);
        startActivity(intent);
    }
    @Override
    public void LoadComplete(List<Recipe> lrecipe) {
        allRecipesAdapter.setRecipes(lrecipe);
    }
    @VisibleForTesting
    public IdlingResourceEx getIdlingresource() {
        if (idlingResourceEx == null) {
            idlingResourceEx = new IdlingResourceEx();
        }
        return  idlingResourceEx;
    }
    @VisibleForTesting
    public void callNetwork(){
        RecipeResources.getRecipes(this,idlingResourceEx);
    }
}
