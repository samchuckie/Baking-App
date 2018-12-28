package com.example.asce.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.bakingapp.Ingredient;
import com.example.asce.bakingapp.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private ArrayList<Ingredient> ingredients = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredient_tv.setText( ingredients.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        if(ingredients.size()!= 0){
            return ingredients.size();
        }
        return 0;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient_tv;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_tv = itemView.findViewById(R.id.ingredient_txt);
        }
    }
}
