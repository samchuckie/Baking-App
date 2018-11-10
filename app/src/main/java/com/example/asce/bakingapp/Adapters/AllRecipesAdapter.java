package com.example.asce.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Recipe;
import java.util.List;

public class AllRecipesAdapter extends RecyclerView.Adapter<AllRecipesAdapter.ViewHolder> {
    private List<Recipe> recipes = null;
    private ItemClickInterface itemClickInterface;

    public AllRecipesAdapter(ItemClickInterface itemClickInterface) {
        this.itemClickInterface=itemClickInterface;
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
    }
    @Override
    public int getItemCount() {
        if(recipes!=null)
        {
            return recipes.size();
        }
        else {
            return 0;
        }
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sRecipe = itemView.findViewById(R.id.recipe_item_rv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
             itemClickInterface.itemClick(recipes.get(getAdapterPosition()));

        }
    }
}
