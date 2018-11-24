package com.example.asce.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class Stepper extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer player;
    int clickedstep;
    String videoUrl;
    Step step;
    Recipe recipe;
    Button prev, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);
        playerView = findViewById(R.id.video_fr);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        Intent intent = getIntent();
        if ((intent != null) && intent.hasExtra("a")) {
            clickedstep = intent.getIntExtra("a", 0);
        }
        if ((intent != null) && intent.hasExtra("b")) {
            recipe = intent.getParcelableExtra("b");
        }
        step = recipe.getSteps().get(clickedstep);
        videoUrl = retrievevideoUrl();
        initializeExoPlayer(videoUrl);
        final int arraysize = recipe.getSteps().size();
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((clickedstep < arraysize) && clickedstep >= 0) {
                    clickedstep -= clickedstep;
                    Intent videointent = new Intent(getApplicationContext(), Stepper.class);
                    videointent.putExtra("a", clickedstep);
                    videointent.putExtra("b", recipe);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry reached end of recipe videos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedstep < arraysize) {
                    clickedstep += clickedstep;
                    Intent videointent = new Intent(getApplicationContext(), Stepper.class);
                    videointent.putExtra("a", clickedstep);
                    videointent.putExtra("b", recipe);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry reached end of recipe videos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String retrievevideoUrl() {
        return step.getVideoURL();
    }

    private void initializeExoPlayer(String videoUrl) {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        player.setPlayWhenReady(true);
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("bakingapp")).createMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    // TODO RELEASE PLAYER
    private void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        release();
    }
}
