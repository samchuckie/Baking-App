package com.example.asce.bakingapp;

import android.content.Intent;
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
        Intent intent = new Intent(this ,Stepper.class);
        intent.putExtra("a" ,step );
        intent.putExtra("b" ,recipe );
        startActivity(intent);
    }
}
