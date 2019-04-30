package com.example.root.videodemo;

import android.content.Context;

import com.example.root.videodemo.widget.MyVideo;

import java.util.ArrayList;
import java.util.List;

public class VideoLab {
    private static VideoLab sVideoLab;
    private List<MyVideo> mMyVideos;

    public List<String> getUrlList() {
        return urlList;
    }

    private List<String> urlList;

    public static VideoLab get(Context context){
        if (sVideoLab == null){
            sVideoLab = new VideoLab(context);
        }
        return sVideoLab;
    }

    private VideoLab(Context context) {
        urlList = new ArrayList<>();
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0");

        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0");
        urlList.add("https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0");


        mMyVideos = new ArrayList<>();

        //生成3个MyVideo
        for(int i = 0; i < 9; i++){
            MyVideo myVideo = new MyVideo();
            myVideo.setDescription("第" + (i+1)  +"个视频");
            myVideo.setUrl(urlList.get(i));
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
