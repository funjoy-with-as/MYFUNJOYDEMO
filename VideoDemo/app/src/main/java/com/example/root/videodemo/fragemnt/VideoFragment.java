package com.example.root.videodemo.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.videodemo.VideoAdapter.VideoAdapter;
import com.example.root.videodemo.VideoLab;
import com.example.root.videodemo.widget.MyVideo;
import com.example.root.videodemo.R;


import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class VideoFragment extends Fragment{



    private List<MyVideo> mMyVideos = VideoLab.get(getActivity()).getMyVideos();
    private VideoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager  mLayoutManager;
    private PagerSnapHelper snapHelper;


    public static VideoFragment newInstance(String videoUrl){
        //创建fragment argument
        //首先创建Bundle对象
        //Bundle args = new Bundle();
        //然后使用Bundle限定类型的put方法，将argument添加到bundle中

        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_list, container, false);


        mRecyclerView = (RecyclerView) v.findViewById(R.id.video_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VideoAdapter(getContext(), mMyVideos);

        snapHelper = new PagerSnapHelper(); //通过SnapHelper来定义对齐规则来辅助RecyclerView在滚动结束时将Item对齐到某个位置
        snapHelper.attachToRecyclerView(mRecyclerView);

        //加入视频到这儿
        mRecyclerView.setLayoutManager(mLayoutManager);
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
