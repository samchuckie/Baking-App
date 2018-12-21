package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.asce.bakingapp.Adapters.StepsAdapter;
import java.util.ArrayList;

import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEY;
import static com.example.asce.bakingapp.Constant.Const.INGRIDIENT;
import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;
import static com.example.asce.bakingapp.Constant.Const.RECIPE_ACTIVITY;
import static com.example.asce.bakingapp.Constant.Const.VIDEO;

public class SpecificRecipe extends AppCompatActivity implements StepsAdapter.StepItemClicked {
    private Recipe recipe;
    private Boolean landscape=false;
    private FragmentManager fragmentManager;
    private VideoFragment videoFragment;
    private String checker = null ;
    private IngredientFragment ingredientFragment;
    ArrayList<Ingredient> ingredient =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_recipe);
        RecyclerView recyclerView = findViewById(R.id.steps_rv);
        StepsAdapter stepsAdapter = new StepsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        fragmentManager= getSupportFragmentManager();
        // TODO Save the state
        if (findViewById(R.id.recipe_frame)!=null)
        {
            landscape=true;
            fragmentManager = getSupportFragmentManager();
            videoFragment = new VideoFragment();
        }
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra(MAIN_ACTIVITY)){
            recipe= intent.getParcelableExtra(MAIN_ACTIVITY);
            ingredient = new ArrayList<>(recipe.getIngredients());

        }
        stepsAdapter.setSteps(recipe.getSteps());
        if (savedInstanceState!=null){
            String key =savedInstanceState.getString(BUNDLE_KEY);
            if (key!=null) {
                switch (key) {
                    case VIDEO:

                        break;
                    case INGRIDIENT:
                        setIngrideintFragment(ingredient);
                        //startIngFragment();
                        break;
                }
            }
        }
        TextView ingredients = findViewById(R.id.ing);
        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (landscape) {
                    setIngrideintFragment(ingredient);
                    if (checker != null) {
                        startIngFragment();
                    }
                    else {
                        checker = INGRIDIENT;
                        fragmentManager.beginTransaction().add(R.id.recipe_frame, ingredientFragment).commit();
                        Log.e("sam" , "ingredients fragment added");

                    }
                } else {
                    Intent ingredientsIntent = new Intent(getApplicationContext(), IngredientsActivity.class);
                    ingredientsIntent.putParcelableArrayListExtra(getResources().getResourceName(R.string.ingredients), ingredient);
                    startActivity(ingredientsIntent);
                }
            }
        });
    }
    private void setIngrideintFragment(ArrayList<Ingredient> ingredient){
        ingredientFragment = new IngredientFragment();
        ingredientFragment.setIngredient(ingredient);
    }
    private void startIngFragment(){
        fragmentManager.beginTransaction().replace(R.id.recipe_frame, this.ingredientFragment).commit();
        Log.e("sam" , "ingredients fragment replaced");
    }
    @Override
    public void stepclicked(int step) {
        if( landscape){
            String url = recipe.getSteps().get(step).getVideoURL();
            if (url.equals("")){
              url = recipe.getSteps().get(step).getThumbnailURL();
            }
            String desc = recipe.getSteps().get(step).getDescription();
            videoFragment.setUrl(url);
            if (checker != null) {
                videoFragment= new VideoFragment();
                videoFragment.setUrl(url);
                videoFragment.setDescription(desc);
                fragmentManager.beginTransaction().replace(R.id.recipe_frame, videoFragment).commit();
            } else {
                checker = VIDEO;
                fragmentManager.beginTransaction().add(R.id.recipe_frame, videoFragment).commit();
            }
        }
        else {
            Intent intent = new Intent(this ,StepsActivity.class);
            intent.putExtra(MAIN_ACTIVITY ,step );
            intent.putExtra(RECIPE_ACTIVITY ,recipe );
            startActivity(intent);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (landscape && checker!=null) {
            switch (checker) {
                case VIDEO :
                outState.putString(BUNDLE_KEY, VIDEO);
                break;
                case INGRIDIENT :
                    outState.putString(BUNDLE_KEY, INGRIDIENT);
                    Log.e("sam" , "Ingerdeint");
                    break;
            }
        }
    }
}
