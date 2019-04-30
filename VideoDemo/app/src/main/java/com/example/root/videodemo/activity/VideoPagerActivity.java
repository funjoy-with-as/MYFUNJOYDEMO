package com.example.root.videodemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.root.videodemo.widget.MyVideo;
import com.example.root.videodemo.R;
import com.example.root.videodemo.VideoLab;
import com.example.root.videodemo.fragemnt.VideoFragment;

import java.util.List;

public class VideoPagerActivity extends AppCompatActivity {
    private static final String EXTRA_VIDEO_URL = "com.example.root.videodemo.video_url";

    private ViewPager mVideoViewPager;
   // @BindView(R.id.video_view_pager) ViewPager mVideoViewPager;
    private List<MyVideo> mMyVideos;


    public static Intent newIntent(Context packageContext, String videoUrl){
        Intent intent = new Intent(packageContext, VideoPagerActivity.class);
        intent.putExtra(EXTRA_VIDEO_URL, videoUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_pager);
        //ButterKnife.bind(this);

        String videoUrl = (String)getIntent().getStringExtra(EXTRA_VIDEO_URL);

        mVideoViewPager = (ViewPager)findViewById(R.id.video_view_pager1);

        //从MyVideoLab中获取数据集
        mMyVideos = VideoLab.get(this).getMyVideos();
        //获取activity的FragmentManager实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        mVideoViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                MyVideo myVideo = mMyVideos.get(position);
                //创建并返回一个有效配置的VideoFragment
                return VideoFragment.newInstance(myVideo.getUrl());
            }

            @Override
            public int getCount() {
                return mMyVideos.size();
            }
        });


    }

}
