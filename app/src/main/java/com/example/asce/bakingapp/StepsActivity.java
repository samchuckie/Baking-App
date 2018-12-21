package com.example.asce.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;
import static com.example.asce.bakingapp.Constant.Const.RECIPE_ACTIVITY;

public class StepsActivity extends AppCompatActivity  {
    private int clickedstep;
    private Recipe recipe;
    private String videsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        Button next = findViewById(R.id.next_vid);
        Button prev = findViewById(R.id.prev_vid);
        VideoFragment videoFragment = new VideoFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        Intent intent = getIntent();
        if((intent!=null)&&(intent.hasExtra(MAIN_ACTIVITY))&&(intent.hasExtra(RECIPE_ACTIVITY)))
        {
            clickedstep = intent.getIntExtra(MAIN_ACTIVITY, 0);
            recipe = intent.getParcelableExtra(RECIPE_ACTIVITY);
            Step step = recipe.getSteps().get(clickedstep);
            String videoUrl = step.getVideoURL();
            if (videoUrl.equals("")){
                videoUrl = step.getThumbnailURL();
            }
            videoFragment.setUrl(videoUrl);
            videsc= step.getDescription();
        }

        if (!videsc.isEmpty()){
            videoFragment.setDescription(videsc);
        }
        else {
            videoFragment.setDescription(getResources().getResourceName(R.string.descrerror));
        }

        fragmentManager.beginTransaction().add(R.id.exo_framer, videoFragment).commit();
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
