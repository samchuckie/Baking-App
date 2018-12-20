package com.example.asce.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asce.bakingapp.Adapters.IngredientAdapter;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.ItemDecoration decoration;
    IngredientAdapter ingredientAdapter;
    private ArrayList<Ingredient> ingredientArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragement_ingredient, container, false);
        // TODO CHANGE LAYOUT FOR THE RECYCLEVIEW
        recyclerView = v.findViewById(R.id.ig_rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        ingredientAdapter= new IngredientAdapter();
        recyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.setIngredients(ingredientArrayList);
        return v;
    }
    public void setIngredient(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientArrayList =ingredientArrayList;
    }
}
