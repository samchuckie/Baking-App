package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.asce.bakingapp.Adapters.StepsAdapter;
import com.example.asce.bakingapp.Fragment.IngredientFragment;
import com.example.asce.bakingapp.Fragment.VideoFragment;
import com.example.asce.bakingapp.Model.Ingredient;
import com.example.asce.bakingapp.Model.Recipe;

import java.util.ArrayList;
import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;
import static com.example.asce.bakingapp.Constant.Const.RECIPE_ACTIVITY;

public class SpecificRecipe extends AppCompatActivity implements StepsAdapter.StepItemClicked {
    private Recipe recipe;
    private Boolean landscape=false;
    private FragmentManager fragmentManager;
    private VideoFragment videoFragment;
    private IngredientFragment ingredientFragment;
    ArrayList<Ingredient> ingredient =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_recipe);
        RecyclerView recyclerView = findViewById(R.id.steps_rv);
        StepsAdapter stepsAdapter = new StepsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        fragmentManager= getSupportFragmentManager();
        if (findViewById(R.id.recipe_frame)!=null)
        {
            landscape=true;
        }
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra(MAIN_ACTIVITY)){
            recipe= intent.getParcelableExtra(MAIN_ACTIVITY);
            ingredient = new ArrayList<>(recipe.getIngredients());
            fragmentManager = getSupportFragmentManager();
            if (ingredientFragment==null && savedInstanceState==null){
                ingredientFragment = new IngredientFragment();
                ingredientFragment.setIngredient(ingredient);
                fragmentManager.beginTransaction().add(R.id.Ingredient_frame, ingredientFragment).commit();
            }
        }
        stepsAdapter.setSteps(recipe.getSteps());
        if( landscape) {
            if(videoFragment==null ) {
                videoFragment= new VideoFragment();
                if (savedInstanceState==null) {
                    String url = recipe.getSteps().get(0).getVideoURL();
                    if (url.equals("")) {
                        url = recipe.getSteps().get(0).getThumbnailURL();
                    }
                    String desc = recipe.getSteps().get(0).getDescription();
                    videoFragment.setUrl(url);
                    videoFragment.setDescription(desc);
                    fragmentManager.beginTransaction().add(R.id.recipe_frame, videoFragment).commit();
                }
            }
        }
    }
    @Override
    public void stepclicked(int step) {
        if( landscape){
            String url = recipe.getSteps().get(step).getVideoURL();
            if (url.equals("")){
              url = recipe.getSteps().get(step).getThumbnailURL();
            }
            String desc = recipe.getSteps().get(step).getDescription();

            //TODO one fragment reuse it

            videoFragment.setUrl(url);
            videoFragment= new VideoFragment();
            videoFragment.setUrl(url);
            videoFragment.setDescription(desc);
            fragmentManager.beginTransaction().replace(R.id.recipe_frame, videoFragment).commit();
        }
        else {
            Intent intent = new Intent(this ,StepsActivity.class);
            intent.putExtra(MAIN_ACTIVITY ,step );
            intent.putExtra(RECIPE_ACTIVITY ,recipe );
            startActivity(intent);
        }
    }

}
