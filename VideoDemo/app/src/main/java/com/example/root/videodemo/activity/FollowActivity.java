package com.example.root.videodemo.activity;

import android.support.v4.app.Fragment;

import com.example.root.videodemo.fragemnt.FellowFragment;


public class FollowActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FellowFragment();
    }
}
