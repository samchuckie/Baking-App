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
        holder.ing_no.setText(String.valueOf(position+1));
        holder.quantity.setText("Quantity: " + String.valueOf(ingredients.get(position).getQuantity()));
        holder.measure.setText("Measure: " + ingredients.get(position).getMeasure());
        holder.ingredient_tv.setText("Ingredient: " + ingredients.get(position).getIngredient());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ing_no,quantity,measure,ingredient_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ing_no = itemView.findViewById(R.id.ing_no);
            quantity = itemView.findViewById(R.id.quantity);
            measure = itemView.findViewById(R.id.measure);
            ingredient_tv = itemView.findViewById(R.id.ingredient_txt);
        }
    }
}
