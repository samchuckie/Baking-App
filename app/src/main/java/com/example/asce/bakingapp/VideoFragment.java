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

import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEY;
import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEYD;
import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEYP;
import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEYU;
import static com.example.asce.bakingapp.Constant.Const.BUNDLE_KEYW;

public class VideoFragment extends Fragment {
    private long currentPosition =0;
    private int currentWindowIndex=0;
    private boolean playwhenready = true;

    public VideoFragment(){
    }
    private String url=null;
    private String description;
    private PlayerView playerView ;
    private SimpleExoPlayer player;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance to true onCreate protect from destroy and recreate and
        // retain the current instance of the fragment when the activity is recreated.
       //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        playerView = v. findViewById(R.id.frag_exo);
        TextView desc = v.findViewById(R.id.descrip_tv);
        TextView missingcontent = v.findViewById(R.id.missing_content);
        if(savedInstanceState!=null)
        {
            currentWindowIndex =savedInstanceState.getInt(BUNDLE_KEYW);
            currentPosition =savedInstanceState.getLong(BUNDLE_KEYP);
            playwhenready = savedInstanceState.getBoolean(BUNDLE_KEY);
            setUrl(savedInstanceState.getString(BUNDLE_KEYU));
            setDescription(savedInstanceState.getString(BUNDLE_KEYD));
            Log.e("sam" , "url is " + url);
        }
        Log.e("sam", "oncreate view");
        desc.setText(getDescription());
        // TODO get utl is non on screen unlock
        if (getUrl()!=null && Util.SDK_INT>23)
        {
            initializeExoPlayer(getUrl());
            Log.e("sam" , "url of first   is " + url);
        }
        else {
            playerView.setVisibility(View.GONE);
            missingcontent.setVisibility(View.VISIBLE);
        }
        return v;
    }
    public void initializeExoPlayer(String videoUrl) {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity().getApplicationContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        Uri uri = Uri.parse(videoUrl);
        player.setPlayWhenReady(playwhenready);
        player.seekTo(currentWindowIndex ,currentPosition);
        Log.e("sam" , "position of new:" + currentPosition);
        Log.e("sam" , "window of new:" + currentWindowIndex);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("bakingapp")).createMediaSource(uri);
        player.prepare(mediaSource, false, false);
        Log.e("sam", "initialized");

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // I have this method as my saveOninstance is called after onStop in tablets thus i have to repeat it
        if(player!=null){
            getConfig();
        }
        if(getUrl()!=null) {
            outState.putInt(BUNDLE_KEYW, currentWindowIndex);
            outState.putLong(BUNDLE_KEYP, currentPosition);
            outState.putBoolean(BUNDLE_KEY, playwhenready);
            outState.putString(BUNDLE_KEYU, getUrl());
            outState.putString(BUNDLE_KEYD, getDescription());
            Log.e("sam" , "saved state");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("sam" , "onstart for fragment video");
    }
        public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
    @Override
    public void onStop() {
        if (player!=null &&Util.SDK_INT >23){
            getConfig();
            release();
            Log.e("sam", "p:" + currentPosition);
            Log.e("sam", "w:" + currentWindowIndex);
            Log.e("sam", "pla:" + playwhenready);
            Log.e("sam", "u:" + getUrl());
            Log.e("sam" , "released" );
        }
        super.onStop();
    }

    private void getConfig() {
        currentPosition = player.getCurrentPosition();
        currentWindowIndex = player.getCurrentWindowIndex();
        playwhenready = player.getPlayWhenReady();
    }

    //    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if (savedInstanceState != null) {
//            currentWindowIndex = savedInstanceState.getInt(BUNDLE_KEYW);
//            currentPosition = savedInstanceState.getLong(BUNDLE_KEYP);
//            playwhenready = savedInstanceState.getBoolean(BUNDLE_KEY);
//            Log.e("sam", "p:" + currentPosition);
//            Log.e("sam", "w:" + currentWindowIndex);
//            Log.e("sam", "pla:" + playwhenready);
//            Log.e("sam", "u:" + savedInstanceState.getString(BUNDLE_KEYU));
//        }
//    }
    @Override
    public void onPause() {
        super.onPause();
        if(player!=null && Util.SDK_INT <=23){
            release();
            Log.e("sam" , "released for pause" );
        }
    }
}
