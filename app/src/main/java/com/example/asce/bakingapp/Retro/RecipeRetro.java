package com.example.asce.bakingapp.Retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.asce.bakingapp.Constant.Const.BASE_URL;

public class RecipeRetro {
    public static Retrofit getinsance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
