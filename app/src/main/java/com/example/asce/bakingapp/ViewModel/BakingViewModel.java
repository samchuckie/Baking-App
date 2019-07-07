package com.example.asce.bakingapp.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.asce.bakingapp.Model.RecipeModel;
import com.example.asce.bakingapp.Model.Recipe;

import java.util.List;

public class BakingViewModel extends ViewModel {
    private RecipeModel recipeModel= new RecipeModel();
    public MutableLiveData<List<Recipe>> getRecipeList() {
        return recipeModel.getMutableRecipe();
    }
}
