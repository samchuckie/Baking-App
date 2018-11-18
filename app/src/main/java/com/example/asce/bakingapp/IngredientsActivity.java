package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.asce.bakingapp.Adapters.IngredientAdapter;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    ArrayList<Ingredient> ingredientArrayList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.ItemDecoration decoration;
    IngredientAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra("ing")){
            ingredientArrayList= intent.getParcelableArrayListExtra("ing");

        }
        recyclerView = findViewById(R.id.ig_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        ingredientAdapter= new IngredientAdapter();
        recyclerView.setAdapter(ingredientAdapter);
        Log.e("sam" , "size is " + ingredientArrayList.size());
        ingredientAdapter.setIngredients(ingredientArrayList);
    }
}
