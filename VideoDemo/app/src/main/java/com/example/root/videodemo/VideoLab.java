package com.example.root.videodemo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class VideoLab {
    private static VideoLab sVideoLab;
    private List<MyVideo> mMyVideos;

    public static VideoLab get(Context context){
        if (sVideoLab == null){
            sVideoLab = new VideoLab(context);
        }
        return sVideoLab;
    }

    private VideoLab(Context context) {
        mMyVideos = new ArrayList<>();

        //生成3个MyVideo
        for(int i = 0; i < 12; i++){
            MyVideo myVideo = new MyVideo();
            myVideo.setDescription("第" + i  +"个视频");
            mMyVideos.add(myVideo);
        }
    }

    public List<MyVideo> getMyVideos(){

        return mMyVideos;
    }

    public MyVideo getMyVideo(String url){
        for (MyVideo myVideo : mMyVideos){
            if(myVideo.getUrl().equals(url)){
                return myVideo;
            }
        }
        return null;
    }
}
