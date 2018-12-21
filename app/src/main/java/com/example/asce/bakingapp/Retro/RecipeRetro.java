package com.example.asce.bakingapp.Retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRetro {
    private static final String BASE_URL="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public static Retrofit getinsance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
