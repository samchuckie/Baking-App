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

public class SpecificRecipe extends AppCompatActivity implements StepsAdapter.StepItemClicked {
    RecyclerView recyclerView;
    StepsAdapter stepsAdapter;
    LinearLayoutManager linearLayoutManager;
    Recipe recipe;
    TextView ingredients;
    Boolean landscape=false;
    FragmentManager fragmentManager;
    VideoFragment videoFragment;
    String checker = null ;
    TextView descrip;
    String url;
    IngredientFragment ingredientFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_recipe);
        recyclerView = findViewById(R.id.steps_rv);
        stepsAdapter = new StepsAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        fragmentManager= getSupportFragmentManager();
        if (findViewById(R.id.recipe_frame)!=null)
        {
            landscape=true;
            fragmentManager = getSupportFragmentManager();
            videoFragment = new VideoFragment();
            descrip =findViewById(R.id.descrip_tv);
        }
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra("a")){
            recipe= intent.getParcelableExtra("a");
        }
        stepsAdapter.setSteps(recipe.getSteps());
        ingredients =findViewById(R.id.ing);
        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Ingredient> ingredient = new ArrayList<>(recipe.getIngredients());
                if (landscape) {
                    ingredientFragment = new IngredientFragment();
                    ingredientFragment.setIngredient(ingredient);
                    if (checker != null) {
                        fragmentManager.beginTransaction().replace(R.id.recipe_frame, ingredientFragment).commit();
                    }
                    else {
                        checker = "added";
                        fragmentManager.beginTransaction().add(R.id.recipe_frame, ingredientFragment).commit();
                    }
                } else {
                    Intent ingredientsIntent = new Intent(getApplicationContext(), IngredientsActivity.class);
                    ingredientsIntent.putParcelableArrayListExtra("ing", ingredient);
                    startActivity(ingredientsIntent);
                }
            }
        });
    }
    @Override
    public void stepclicked(int step) {
        if( landscape){
            url = recipe.getSteps().get(step).getVideoURL();
            if (url.equals("")){
              url = recipe.getSteps().get(step).getThumbnailURL();
            }
            String desc = recipe.getSteps().get(step).getDescription();
            videoFragment.setUrl(url);
            if (checker != null) {
                videoFragment= new VideoFragment();
                videoFragment.setUrl(url);
                fragmentManager.beginTransaction().replace(R.id.recipe_frame, videoFragment).commit();
            } else {
                Log.e("sam", "checker is null");
                checker = "added";
                fragmentManager.beginTransaction().add(R.id.recipe_frame, videoFragment).commit();

            }
            descrip.setText(desc);
        }
        else {
            Intent intent = new Intent(this ,StepsActivity.class);
            intent.putExtra("a" ,step );
            intent.putExtra("b" ,recipe );
            startActivity(intent);
        }
    }
}
