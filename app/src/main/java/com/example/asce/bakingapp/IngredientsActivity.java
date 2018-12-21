package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private ArrayList<Ingredient> ingredientArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        if ((intent!=null) && intent.hasExtra(getResources().getResourceName(R.string.ingredients))){
            ingredientArrayList= intent.getParcelableArrayListExtra(getResources().getResourceName(R.string.ingredients));
        }
        IngredientFragment ingredientFragment = new IngredientFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        ingredientFragment.setIngredient(ingredientArrayList);
        fragmentManager.beginTransaction().add(R.id.ingredient_framelayout, ingredientFragment).commit();

    }
}
