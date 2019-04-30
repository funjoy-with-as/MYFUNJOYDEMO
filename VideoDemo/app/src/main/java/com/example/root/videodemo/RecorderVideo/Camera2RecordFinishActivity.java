package com.example.root.videodemo.RecorderVideo;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.cktim.camera2library.Camera2Config;
import com.example.root.videodemo.R;
//这个activity对视频或者照片做进一步处理

public class Camera2RecordFinishActivity extends AppCompatActivity implements View.OnClickListener{
    private String picPath;//图片地址
    private String videoPath;//视频地址

    private ImageView iv;
    private ImageButton ibtn_ok;
    private ImageButton ibtn_cancel;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //全屏模式
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.activity_record_detail);

        iv = findViewById(R.id.iv);
        ibtn_ok = (ImageButton) findViewById(R.id.ibtn_sureSelect);
        videoView = (VideoView) findViewById(R.id.vv_showVideo);
        ibtn_cancel = (ImageButton) findViewById(R.id.ibtn_cancel);
        ibtn_cancel.setOnClickListener(this);
        ibtn_ok.setOnClickListener(this);

        if (getIntent() != null) {
            //获取传递过来的图片地址
            picPath = getIntent().getStringExtra(Camera2Config.INTENT_PATH_SAVE_PIC);
            if (TextUtils.isEmpty(picPath)) {
                iv.setVisibility(View.GONE);
            } else {
                iv.setVisibility(View.VISIBLE);
                iv.setImageBitmap(BitmapFactory.decodeFile(picPath));
            }

            //获取传递过来的视频地址
            videoPath = getIntent().getStringExtra(Camera2Config.INTENT_PATH_SAVE_VIDEO);
            if (!TextUtils.isEmpty(videoPath)){
                videoView.setVisibility(View.VISIBLE);
                videoView.setVideoPath(videoPath);
                mediaController = new MediaController(this);
                videoView.setMediaController(mediaController);
                videoView.start();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_cancel:{
                finish();
            }
            case R.id.ibtn_sureSelect:{
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("确认上传？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //上传的代码
                            }
                        })
                        .create();
                dialog.show();
            }
        }

    }
}
