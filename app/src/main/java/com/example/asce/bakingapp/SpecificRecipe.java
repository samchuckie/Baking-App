package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.asce.bakingapp.Adapters.StepsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpecificRecipe extends AppCompatActivity implements StepsAdapter.StepItemClicked {
    RecyclerView recyclerView;
    StepsAdapter stepsAdapter;
    DividerItemDecoration decoration;
    LinearLayoutManager linearLayoutManager;
    Recipe recipe;
    TextView ingredients;
    Boolean landscape;
    FragmentManager fragmentManager;
    VideoFragment videoFragment;
    String checker = null ;
    TextView descrip;
    // TODO On rotation add the check to saved instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_recipe);
        // TODO set app title bar to the current resume
        recyclerView = findViewById(R.id.steps_rv);
        stepsAdapter = new StepsAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
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
                Intent ingredientsIntent =  new Intent(getApplicationContext(),IngredientsActivity.class);
                ArrayList<Ingredient> ingredient=new ArrayList<>(recipe.getIngredients());
                ingredientsIntent.putParcelableArrayListExtra("ing",ingredient);
                startActivity(ingredientsIntent);
            }
        });
    }

    @Override
    public void stepclicked(int step) {
        if(landscape!=null){
            if(landscape) {
                String url = recipe.getSteps().get(step).getVideoURL();
                String desc = recipe.getSteps().get(step).getDescription();
                videoFragment.setUrl(url);
                if (checker != null) {
                    Log.e("sam", "checker is " + checker);
                   // videoFragment.release();
                    videoFragment= new VideoFragment();
                    videoFragment.setUrl(url);
                    getSupportFragmentManager().beginTransaction().replace(R.id.recipe_frame, videoFragment).commit();

                } else {
                    Log.e("sam", "checker is nul    l");
                    checker = "added";
                    fragmentManager.beginTransaction().add(R.id.recipe_frame, videoFragment).commit();

                }
                descrip.setText(desc);
            }
        }
        else {
            Intent intent = new Intent(this ,StepsActivity.class);
            intent.putExtra("a" ,step );
            intent.putExtra("b" ,recipe );
            startActivity(intent);
        }
    }
}
