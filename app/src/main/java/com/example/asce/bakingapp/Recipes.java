package com.example.asce.bakingapp;

import java.util.List;

public class Recipes {
    public Recipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    List<Recipe> recipes = null;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipe(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

