package com.example.asce.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoFragment extends Fragment {

    public VideoFragment(){
    }
    String url;
    PlayerView playerView ;
    SimpleExoPlayer player;
    TextView missingcontent;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance to true onCreate protect from destroy and recreate and
        // retain the current instance of the fragment when the activity is recreated.
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        playerView = v. findViewById(R.id.frag_exo);
        missingcontent = v. findViewById(R.id.missing_content);
        Log.e("sam" , "Oncreateview");
        return v;
    }
    public void initializeExoPlayer(String videoUrl) {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity().getApplicationContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("bakingapp")).createMediaSource(uri);
        //player.seekTo(8200);
        player.setPlayWhenReady(true);
        player.prepare(mediaSource, true, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!getUrl().equals("")&& Util.SDK_INT>23)
        {
            initializeExoPlayer(getUrl());
        }
        else {
            playerView.setVisibility(View.GONE);
            missingcontent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
            Log.e("sam" , "PLayer is not null and s the player is released");
        }
        else {
            Log.e("sam" , "Player is NULL NO release");

        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if(Util.SDK_INT >23){
            release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("sam" , "ondestroy for fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT >23){
            release();
        }
    }
}
