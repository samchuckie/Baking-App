package com.example.asce.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StepsActivity extends AppCompatActivity  {
    VideoFragment videoFragment;
    int clickedstep;
    String videoUrl;
    Step step;
    Recipe recipe;
    Context context;
    Button prev,next;
    TextView desc;
    String videsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        next =  findViewById(R.id.next_vid);
        prev =  findViewById(R.id.prev_vid);
        desc =findViewById(R.id.vid_desc);

        videoFragment= new VideoFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        Intent intent = getIntent();
        context=getApplicationContext();
        if((intent!=null)&&(intent.hasExtra("a"))&&(intent.hasExtra("b")))
        {
            clickedstep = intent.getIntExtra("a", 0);
            recipe = intent.getParcelableExtra("b");
            step = recipe.getSteps().get(clickedstep);
            videoUrl = step.getVideoURL();
            videoFragment.setUrl(videoUrl);
            videsc= step.getDescription();
            Log.e("sam", "clicked index is " + clickedstep);
        }

        if (!videsc.isEmpty()){
            desc.setText(videsc);
        }
        else {
            desc.setText("No description available");
        }

        fragmentManager.beginTransaction().add(R.id.exo_framer,videoFragment).commit();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,StepsActivity.class);
                Log.e("sam", "Index is " + clickedstep);
                clickedstep++;
                if (clickedstep<recipe.getSteps().size()){
                    intent.putExtra("a" ,clickedstep );
                    intent.putExtra("b" ,recipe );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached end of steps" , Toast.LENGTH_SHORT).show();
                }
                // TODO GO THROUGH THE LIST AND CHOOSE NEXT


            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,StepsActivity.class);
                Log.e("sam", "Index is " + clickedstep);

                if (clickedstep>0){
                    clickedstep--;
                    intent.putExtra("a" ,clickedstep );
                    intent.putExtra("b" ,recipe );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached beginning of steps" , Toast.LENGTH_SHORT).show();
                }
                // TODO GO THROUGH THE LIST AND CHOOSE NEXT


            }
        });
    }
}
