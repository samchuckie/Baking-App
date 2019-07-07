package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asce.bakingapp.Fragment.VideoFragment;
import com.example.asce.bakingapp.Model.Recipe;
import com.example.asce.bakingapp.Model.Step;

import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;
import static com.example.asce.bakingapp.Constant.Const.RECIPE_ACTIVITY;

public class StepsActivity extends AppCompatActivity  {
    private int clickedstep;
    private Recipe recipe;
    private VideoFragment videoFragmenter;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        TextView next = findViewById(R.id.next_vid);
        TextView prev = findViewById(R.id.prev_vid);
        fragmentManager= getSupportFragmentManager();
        Intent intent = getIntent();
        if((intent!=null)&&(intent.hasExtra(MAIN_ACTIVITY))&&(intent.hasExtra(RECIPE_ACTIVITY) )) {
            clickedstep = intent.getIntExtra(MAIN_ACTIVITY, 0);
            recipe = intent.getParcelableExtra(RECIPE_ACTIVITY);
        }
        if(videoFragmenter==null) {
            videoFragmenter = new VideoFragment();
            if (savedInstanceState == null) {
                Step step = recipe.getSteps().get(clickedstep);
                String videoUrl = step.getVideoURL();
                if (videoUrl.equals("")) {
                    videoUrl = step.getThumbnailURL();
                }
                videoFragmenter.setUrl(videoUrl);
                String videsc = step.getDescription();
                if (!videsc.isEmpty()) {
                    videoFragmenter.setDescription(videsc);
                } else {
                    videoFragmenter.setDescription(getResources().getResourceName(R.string.descrerror));
                }
                fragmentManager.beginTransaction().add(R.id.exo_framer, videoFragmenter).commit();
            }
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,StepsActivity.class);
                clickedstep++;
                if (clickedstep<recipe.getSteps().size()){
                    intent.putExtra(MAIN_ACTIVITY ,clickedstep );
                    intent.putExtra(RECIPE_ACTIVITY ,recipe );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached end of steps" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,StepsActivity.class);
                if (clickedstep>0){
                    clickedstep--;
                    intent.putExtra(MAIN_ACTIVITY,clickedstep );
                    intent.putExtra(RECIPE_ACTIVITY ,recipe );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached beginning of steps" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
