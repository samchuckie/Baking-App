package com.example.asce.bakingapp.Retro;

import com.example.asce.bakingapp.Model.Recipe;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesInt {
    @GET("baking.json")
    Call<List<Recipe>> getall();
}
