package com.example.root.videodemo.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.videodemo.MyVideo;
import com.example.root.videodemo.MyVideoPlayer;
import com.example.root.videodemo.R;
import com.example.root.videodemo.VideoLab;

import java.util.UUID;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoFragment extends Fragment {
    private static final String ARG_VIDEO_URL = "video_url";

    private TextView mAuthorTextView;
    private TextView mDescriptionTextView;

    private MyVideo mMyVideo;
    private MyVideoPlayer mMyVideoPlayer;

//    @BindView(R.id.author_text_view) TextView mAuthorTextView;
//    @BindView(R.id.description_text_view) TextView mDescriptionTextView;
    //@BindView(R.id.video_player) MyVideoPlayer mMyVideoPlayer;


    public static VideoFragment newInstance(String videoUrl){
        //创建fragment argument
        //首先创建Bundle对象
        Bundle args = new Bundle();
        //然后使用Bundle限定类型的put方法，将argument添加到bundle中
        args.putSerializable(ARG_VIDEO_URL, videoUrl);

        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取extra数据并取得MyVideo对象

        //从argument中获取MyVideo url
        //String videoUrl = (String) getArguments().getString(ARG_VIDEO_URL);
        mMyVideo = new MyVideo();
        //mMyVideo = VideoLab.get(getActivity()).getMyVideo(videoUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        //设置视频作者
        mAuthorTextView = (TextView)v.findViewById(R.id.author_text_view);
        mAuthorTextView.setText(mMyVideo.getAuthor());
        //设置视频描述
        mDescriptionTextView = (TextView)v.findViewById(R.id.description_text_view);
        mDescriptionTextView.setText(mMyVideo.getDescription());

        mMyVideoPlayer = (MyVideoPlayer)v.findViewById(R.id.video_player);
        mMyVideoPlayer.setUp("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,mMyVideo.getDescription());


        return v;
    }

}
