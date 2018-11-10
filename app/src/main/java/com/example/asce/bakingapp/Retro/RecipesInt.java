package com.example.asce.bakingapp.Retro;

import com.example.asce.bakingapp.Recipes;
import com.example.asce.bakingapp.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesInt {
    @GET("baking.json")
    Call<List<Recipe>> getall();}
