package com.example.asce.bakingapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.bakingapp.R;
import com.example.asce.bakingapp.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Step> steps = null;
    StepItemClicked stepItemClicked;
    public StepsAdapter(StepItemClicked stepItemClicked) {
        this.stepItemClicked = stepItemClicked;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.steps_items, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(steps.get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (steps != null) {
            return steps.size();
        } else {
            return 0;
        }
    }
    public interface StepItemClicked {
        void stepclicked(int step);
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.steps_item_rv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            stepItemClicked.stepclicked(getAdapterPosition());
        }
    }
}