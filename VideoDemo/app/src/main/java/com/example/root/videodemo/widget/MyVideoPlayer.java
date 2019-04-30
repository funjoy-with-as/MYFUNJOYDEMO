package com.example.root.videodemo.widget;

import android.content.Context;
import android.util.AttributeSet;

import cn.jzvd.JZVideoPlayerStandard;

public class MyVideoPlayer extends JZVideoPlayerStandard{
    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context){
        super.init(context);
    }
}
