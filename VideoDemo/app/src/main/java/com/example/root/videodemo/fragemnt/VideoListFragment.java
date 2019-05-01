package com.example.root.videodemo.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.videodemo.R;
import com.example.root.videodemo.VideoLab;
import com.example.root.videodemo.activity.VideoPagerActivity;
import com.example.root.videodemo.widget.MyVideo;

import java.util.List;

public class VideoListFragment extends Fragment {
    private RecyclerView mVideoRecyclerView;
    private VideoAdapter mAdapter; //自定义Adapter类

   // @BindView(R.id.video_recycler_view) RecyclerView mVideoRecyclerView;
//    @BindView(R.id.author_text_view) TextView mAuthorTextView;
//    @BindView(R.id.description_text_view) TextView mDescriptionTextView;


    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_video_list,container,false);
        //ButterKnife.bind(this, view);

        mVideoRecyclerView = (RecyclerView)view.findViewById(R.id.video_recycler_view);
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    //在onResume()方法中刷新列表项
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        VideoLab videoLab = VideoLab.get(getActivity());
        List<MyVideo> myVideos = videoLab.getMyVideos();
        if (mAdapter == null){
            mAdapter = new VideoAdapter(myVideos);
            mVideoRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    //RecyclerView自身并不会创建视图，由ViewHolder创建
    //在VideoHolder中aaa处理点击事件
    private class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mAuthorTextView;
        private TextView mDescriptionTextView;
        private MyVideo mMyVideo;

        //构造方法
        public VideoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_video, parent, false));
            itemView.setOnClickListener(this);

           mAuthorTextView = (TextView)itemView.findViewById(R.id.author_text_view);
            mDescriptionTextView = (TextView)itemView.findViewById(R.id.description_text_view);

        }

        //绑定列表项
        public void bind(MyVideo myVideo) {
            mMyVideo = myVideo;
            mAuthorTextView.setText(mMyVideo.getAuthor());
            mDescriptionTextView.setText(mMyVideo.getDescription());
        }

        @Override
        public void onClick(View v) {
            //显示当前MyVideo的详细信息,配置启动VideoPagerActivity
            //Intent intent = VideoPagerActivity.newIntent(getActivity(), mMyVideo.getUrl());
            //Intent intent = new Intent(getActivity(), VideoActivity.class);

            //配置启动VideoPagerActivity
            Intent intent = VideoPagerActivity.newIntent(getActivity(), mMyVideo.getUrl());
            startActivity(intent);
        }
    }

    //定义Adapter内部类
    private class VideoAdapter extends RecyclerView.Adapter<VideoHolder> {
        private List<MyVideo> mMyVideos;

        public VideoAdapter(List<MyVideo> myVideos) {
            mMyVideos = myVideos;
        }

        @NonNull
        @Override
        public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VideoHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoHolder videoHolder, int i) {
            //调用bind(MyVideo)方法
            MyVideo myVideo = mMyVideos.get(i);
            videoHolder.bind(myVideo);

        }

        @Override
        public int getItemCount() {
            return mMyVideos.size();
        }
    }
}
