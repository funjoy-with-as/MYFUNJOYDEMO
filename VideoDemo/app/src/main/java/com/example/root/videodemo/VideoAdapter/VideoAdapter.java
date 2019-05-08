package com.example.root.videodemo.VideoAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.videodemo.R;
import com.example.root.videodemo.activity.MainActivity;
import com.example.root.videodemo.widget.MyVideo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private Context mContext;
    private List<MyVideo> mMyVideos;

    public VideoAdapter(Context context, List<MyVideo> myVideos) {
        mContext = context;
        mMyVideos = myVideos;
    }

    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_video,viewGroup,false);
        return new VideoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder videoHolder, int i) {
        //调用bind(MyVideo)方法
        MyVideo myVideo = mMyVideos.get(i);
        videoHolder.bind(myVideo);
        videoHolder.mMyVideoPlayer.setUp(mMyVideos.get(i).getUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
    }
    @Override
    public int getItemCount() {
        return mMyVideos.size();
    }

public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public JZVideoPlayerStandard mMyVideoPlayer; //android提供统一的接口MediaMetadataRetriever解析媒体文件、获取媒体文件中取得帧和元数据（视频/音频包含的标题、格式、艺术家等信息）。

    MediaMetadataRetriever media = new MediaMetadataRetriever();

    //构造方法
    public VideoHolder(View view) {
        super(view);
        mMyVideoPlayer = view.findViewById(R.id.video_player);
        itemView.setOnClickListener(this);
    }

    //绑定列表项
    public void bind(MyVideo myVideo) {
        //获取视频第一帧图片
        media.setDataSource(myVideo.getUrl(),new HashMap<String, String>()); //使用加载在线资源的方法
        Bitmap bitmap = media.getFrameAtTime();
        //设置略缩图
        mMyVideoPlayer.thumbImageView.setImageBitmap(bitmap);

        mMyVideoPlayer.setUp(myVideo.getUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_LIST,"");
        mMyVideoPlayer.startVideo();//视频加载好后自动播放
        //mMyVideoPlayer.onStatePreparing(); //视频加载好后自动播放

    }

    @Override
    public void onClick(View v) {
    }
}
}

