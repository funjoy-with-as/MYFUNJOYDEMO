package com.example.root.videodemo.fragemnt;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.videodemo.VideoAdapter.VideoAdapter;
import com.example.root.videodemo.VideoAdapter.VideoLab;
import com.example.root.videodemo.VideoAdapter.MyVideo;
import com.example.root.videodemo.R;


import java.util.ArrayList;

import cn.jzvd.JZVideoPlayer;

public class VideoFragment extends Fragment{

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager  mLayoutManager;
    private ArrayList<MyVideo> mMyVideos= VideoLab.newInstance().getMyVideos();
    private PagerSnapHelper snapHelper;

    public VideoFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_list, container, false);

        Log.i("视频苏亮",mMyVideos.size()+"-----------------------------------");

        mRecyclerView = (RecyclerView) v.findViewById(R.id.video_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VideoAdapter(getContext(),mMyVideos);
        snapHelper = new PagerSnapHelper(); //通过SnapHelper来定义对齐规则来辅助RecyclerView在滚动结束时将Item对齐到某个位置
        snapHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }


    private void addListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(mLayoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof VideoAdapter.VideoHolder) {
                            ((VideoAdapter.VideoHolder) viewHolder).mMyVideoPlayer.startVideo();
                        }

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
    }

}
