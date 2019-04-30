package com.example.root.videodemo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.videodemo.R;
import com.example.root.videodemo.RecorderVideo.CameraActivity;
import com.example.root.videodemo.VideoLab;
import com.example.root.videodemo.widget.MyVideo;
import com.example.root.videodemo.widget.MyVideoPlayer;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_VIDEO_CAPTURE = 1;

    private RecyclerView mVideoRecyclerView;
    private VideoTAdapter mVideoAdapter; //自定义类
    private LinearLayoutManager mLinearLayoutManager;
//    private MyVideoPlayer mMyVideoPlayer;

    private PagerSnapHelper snapHelper;     //用于滑动后的定位辅助类

    private TextView tv_home,tv_concerned,tv_message,tv_mine;
    private ImageView iv_add;

    private boolean isLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        if(isLogine){
//            initListener();
//        }else{
//            Intent intent_login = new Intent(MainActivity.this,LoginPage.class);
//            startActivity(intent_login);
//            initListener();
//            isLogine = true;
//        }

    }



    private void initView() {
        mVideoRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false);

        tv_home = (TextView)findViewById(R.id.navigationBar_home);
        tv_concerned = (TextView) findViewById(R.id.navigationBar_concerned);
        tv_message = (TextView) findViewById(R.id.navigationBar_messages);
        tv_mine = (TextView) findViewById(R.id.navigationBar_mine);
        iv_add = (ImageView) findViewById(R.id.navigationBar_add);
        tv_home.setOnClickListener(this);
        tv_concerned.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
        iv_add.setOnClickListener(this);


        snapHelper = new PagerSnapHelper(); //通过SnapHelper来定义对齐规则来辅助RecyclerView在滚动结束时将Item对齐到某个位置
        snapHelper.attachToRecyclerView(mVideoRecyclerView);

       //创建一个VideoTAdapter实例
        //从MyVideoLab中获取数据集
        mVideoAdapter = new VideoTAdapter(VideoLab.get(this).getMyVideos());
        mVideoRecyclerView.setLayoutManager(mLinearLayoutManager);
        mVideoRecyclerView.setAdapter(mVideoAdapter);


    }

    private void addListener() {

        mVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(mLinearLayoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof VideoTHolder) {
                            ((VideoTHolder) viewHolder).mVideoView.startVideo();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigationBar_home:
                Intent intent1 = new Intent(MainActivity.this, VideoListActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.navigationBar_concerned:
                Intent intent2  = new Intent(MainActivity.this, FollowActivity.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.navigationBar_add:
                Intent intent3  = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.navigationBar_messages:
                Intent intent4 = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.navigationBar_mine:
                Intent intent5  = new Intent(MainActivity.this, SelfActivity.class);
                startActivity(intent5);
                finish();
                break;
        }
    }

//
//    /**
//     * 播放视频
//     */
//    private void playVideo(int position, View view) {
//        if (view != null) {
//            mMyVideoPlayer = view.findViewById(R.id.video_view);
//            mMyVideoPlayer.startVideo();
//        }
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mMyVideoPlayer != null) {
//            mMyVideoPlayer.onStatePause();
//        }
//    }

//    //RecyclerView自身并不会创建视图，由ViewHolder创建
//    //在VideoHolder中处理点击事件
//    private class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView mAuthorTextView;
//        private TextView mDescriptionTextView;
//        private MyVideo mMyVideo;
//
//        //构造方法
//        public VideoHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.list_item_video, parent, false));
//            itemView.setOnClickListener(this);
//
//            mAuthorTextView = (TextView)itemView.findViewById(R.id.author_text_view);
//            mDescriptionTextView = (TextView)itemView.findViewById(R.id.description_text_view);
//
//        }
//
//        //绑定列表项
//        public void bind(MyVideo myVideo) {
//            mMyVideo = myVideo;
//            mAuthorTextView.setText(mMyVideo.getAuthor());
//            mDescriptionTextView.setText(mMyVideo.getDescription());
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }



    //定义Adapter内部类
    private class VideoTAdapter extends RecyclerView.Adapter<VideoTHolder> {
        private List<MyVideo> mMyVideos;

        //构造函数
        public VideoTAdapter(List<MyVideo> myVideos) {
            mMyVideos = myVideos;
        }

        @NonNull
        @Override
        public VideoTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VideoTHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.video_player_layout,null));
        }


        @Override
        public void onBindViewHolder(@NonNull VideoTHolder videoTHolder, int i) {
            //调用bind(MyVideo)方法
            MyVideo myVideo = mMyVideos.get(i);
            videoTHolder.bind(myVideo);

        }

        @Override
        public int getItemCount() {
            return mMyVideos.size();
        }
    }



    //RecyclerView自身并不会创建视图，由ViewTHolder创建\
    //实例化并使用video_player_layout布局
   private class VideoTHolder extends RecyclerView.ViewHolder{
        public MyVideoPlayer mVideoView;
       // public View rootView;
        //构造方法
        public VideoTHolder(View itemView) {
            super(itemView);
            //rootView = itemView;
            mVideoView = itemView.findViewById(R.id.video_view);
            //mVideoView.setUp("https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "ksjck");

        }
        //绑定列表项
        public void bind(MyVideo myVideo) {
           mVideoView.setUp(myVideo.getUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, myVideo.getDescription());

        }

    }

}
