package com.example.asce.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllRecipesAdapter extends RecyclerView.Adapter<AllRecipesAdapter.ViewHolder> {
    private List<Recipe> recipes = null;
    private ItemClickInterface itemClickInterface;
    private Context context;

    public AllRecipesAdapter(ItemClickInterface itemClickInterface, Context context) {
        this.itemClickInterface = itemClickInterface;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_items,viewGroup,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.sRecipe.setText(recipes.get(i).getName());
        String recipename = recipes.get(i).getName();
        // Using local resources as api doesnt provide any
        switch (recipename){
            case "Nutella Pie":
                Picasso.get().load(R.drawable.nutella).into(viewHolder.recipe_iv);
                break;
            case "Brownies":
                Picasso.get().load(R.drawable.brownies).into(viewHolder.recipe_iv);
                break;
            case "Yellow Cake":
                Picasso.get().load(R.drawable.yellowcake).into(viewHolder.recipe_iv);
                break;
            case "Cheesecake":
                Picasso.get().load(R.drawable.cheesecake).into(viewHolder.recipe_iv);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return (recipes!=null)?recipes.size():0;
    }
    public interface ItemClickInterface{
        void itemClick(Recipe recipe);
    }
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sRecipe;
        ImageView recipe_iv;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            sRecipe = itemView.findViewById(R.id.recipe_item_rv);
            recipe_iv = itemView.findViewById(R.id.recipe_iv);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
             itemClickInterface.itemClick(recipes.get(getAdapterPosition()));
        }
    }
}
