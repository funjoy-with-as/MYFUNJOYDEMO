package com.example.root.videodemo.VideoAdapter;

import android.util.Log;

import com.example.root.videodemo.VideoAdapter.MyVideo;

import java.util.ArrayList;

public class VideoLab {
    private static ArrayList<MyVideo> mMyVideos;
    private static VideoLab videoLab = null;

    private VideoLab() {
        mMyVideos = new ArrayList<>();
//            mMyVideos.add(new MyVideo("https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
        mMyVideos.add(new MyVideo("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
        mMyVideos.add(new MyVideo("https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
        mMyVideos.add(new MyVideo("https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
        mMyVideos.add(new MyVideo("https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
        mMyVideos.add(new MyVideo("http://192.168.43.145:8080/examples/3.mp4"));
    }

    public static VideoLab newInstance() {
        if (videoLab == null) {
            synchronized (VideoLab.class) {
                if (videoLab == null) {
                    videoLab = new VideoLab();
                }
            }
        }
        return videoLab;
    }

    public static ArrayList<MyVideo> getMyVideos() {
        return mMyVideos;
    }

    public static void addVideo(String url) {
        mMyVideos.add(new MyVideo(url));
        Log.i("视频地址", url);

    }

}
