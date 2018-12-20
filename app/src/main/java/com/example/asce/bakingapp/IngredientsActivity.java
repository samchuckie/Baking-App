package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
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
    IngredientFragment ingredientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra("ing")){
            ingredientArrayList= intent.getParcelableArrayListExtra("ing");
        }
        ingredientFragment = new IngredientFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        ingredientFragment.setIngredient(ingredientArrayList);
        fragmentManager.beginTransaction().add(R.id.ingredient_framelayout, ingredientFragment).commit();

    }
}
