package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.asce.bakingapp.Adapters.StepsAdapter;

public class SpecificRecipe extends AppCompatActivity implements StepsAdapter.StepItemClicked {
    RecyclerView recyclerView;
    StepsAdapter stepsAdapter;
    DividerItemDecoration decoration;
    LinearLayoutManager linearLayoutManager;
    Recipe recipe;

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

    }

    @Override
    public void stepclicked(int step) {
        Intent intent = new Intent(this ,Stepper.class);
        intent.putExtra("a" ,step );
        intent.putExtra("b" ,recipe );
        startActivity(intent);
    }
}
