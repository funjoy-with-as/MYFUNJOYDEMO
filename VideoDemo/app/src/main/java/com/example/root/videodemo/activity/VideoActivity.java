package com.example.root.videodemo.activity;

import android.support.v4.app.Fragment;

import com.example.root.videodemo.fragemnt.VideoFragment;

public class VideoActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {

        return new VideoFragment();
    }
}
