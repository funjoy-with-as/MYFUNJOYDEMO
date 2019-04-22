package com.example.douyin;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//ijkplayer是Bilibili基于ffmpeg开发并开源的轻量级视频播放器，支持播放本地网络视频，也支持流媒体播放。

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.example.douyin.RecorderVideo.CameraActivity;
import com.example.douyin.VideoLists.DataUtils;
import com.example.douyin.widget.OnViewPagerListener;
import com.example.douyin.widget.PagerLayoutManager;
import com.example.douyin.VideoLists.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //动态权限申请
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA
            ,Manifest.permission.RECORD_AUDIO
            };
    private AlertDialog dialog;


    static final int REQUEST_VIDEO_CAPTURE = 1;

    private RecyclerView recyclerView;
    private ArrayList<Bean> mDatas = new ArrayList<>();
    private VideoAdapter mAdapter;
    private IjkVideoView mVideoView;
    private PagerLayoutManager mLayoutManager;

    private TextView tv_home,tv_concernd,tv_message,tv_mine;
    private ImageView iv_add;

    private boolean isLogine = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化界面

//        //请求权限
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            //检查该权限是否已经获取
//            int p1 = ContextCompat.checkSelfPermission(this,permissions[0]);
//            int p2 = ContextCompat.checkSelfPermission(this,permissions[1]);
//            int p3 = ContextCompat.checkSelfPermission(this,permissions[2]);
//            if()
//        }



        if(isLogine){
            initListener();
        }else{
            Intent intent_login = new Intent(MainActivity.this,LoginPage.class);
            startActivity(intent_login);
            initListener();
            isLogine = true;
        }
    }
    private void initListener(){
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete(View view) {
                playVideo(0, view);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom, View view) {
                playVideo(position, view);
            }

            @Override
            public void onPageRelease(boolean isNext, int position, View view) {
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(view);
            }
        });
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler_view);
        tv_home = (TextView)findViewById(R.id.navigationBar_home);
        tv_concernd = (TextView) findViewById(R.id.navigationBar_concerned);
        tv_message = (TextView) findViewById(R.id.navigationBar_messages);
        tv_mine = (TextView) findViewById(R.id.navigationBar_mine);
        iv_add = (ImageView) findViewById(R.id.navigationBar_add);
        tv_home.setOnClickListener(this);
        tv_concernd.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
        iv_add.setOnClickListener(this);

        mLayoutManager = new PagerLayoutManager(this, OrientationHelper.VERTICAL);
        mDatas.addAll(DataUtils.getDatas());//加入视频数据
        mAdapter = new VideoAdapter(this, mDatas);//设置
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigationBar_home:{
                Toast.makeText(MainActivity.this,"主页",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigationBar_concerned:
                Toast.makeText(MainActivity.this,"关注",Toast.LENGTH_SHORT).show();
//                Intent intent3 = new Intent(MainActivity.this, TestRecorder.class);
//                startActivity(intent3);
                break;
            case R.id.navigationBar_add:
                Toast.makeText(MainActivity.this,"发布",Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(MainActivity.this,VideoFromDevice.class);
//                startActivity(intent1);
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(takeVideoIntent,REQUEST_VIDEO_CAPTURE);
                }

                break;
            case R.id.navigationBar_messages:
                Toast.makeText(MainActivity.this,"消息",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, MediaRecorderActivity.class);
//                startActivity(intent);
                break;
            case R.id.navigationBar_mine:
                Toast.makeText(MainActivity.this,"需要请求权限",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent2);
                break;
        }
    }

    /**
     * 播放视频
     */
    private void playVideo(int position, View view) {
        if (view != null) {
            mVideoView = view.findViewById(R.id.video_view);
            mVideoView.start();
        }
    }

    /**
     * 停止播放
     */
    private void releaseVideo(View view) {
        if (view != null) {
            IjkVideoView videoView = view.findViewById(R.id.video_view);
            videoView.stopPlayback();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoViewManager.instance().releaseVideoPlayer();
    }
}
