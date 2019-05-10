package com.example.root.videodemo.RecorderVideo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.cktim.camera2library.Camera2Config;
import com.example.root.videodemo.R;


import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;
//这个activity对视频或者照片做进一步处理

public class Camera2RecordFinishActivity extends AppCompatActivity{
//    private String picPath;//图片地址
    private String videoPath;//视频地址

    Fragment uploadFragment;


    FragmentManager fragmentManager;//fragment管理器
    FragmentTransaction fragmentTransaction;//事物管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //全屏模式
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record_detail);


        if (getIntent() != null) {
//            //获取传递过来的图片地址
//            picPath = getIntent().getStringExtra(Camera2Config.INTENT_PATH_SAVE_PIC);
//            if (TextUtils.isEmpty(picPath)) {
//                uploadFragment = UploadFragment.newInstance(picPath);
////            } else {
////                iv.setImageBitmap(BitmapFactory.decodeFile(picPath));
//            }
                //获取传递过来的视频地址
                videoPath = getIntent().getStringExtra(Camera2Config.INTENT_PATH_SAVE_VIDEO);
                if (!TextUtils.isEmpty(videoPath)) {
                    Toast.makeText(Camera2RecordFinishActivity.this, "视频地址为" + videoPath, Toast.LENGTH_LONG).show();
                    Log.i("路径",videoPath);
                    Intent intent = new Intent(this,UploadActivity.class);
                    intent.putExtra("path",videoPath);
                    startActivity(intent);
                    finish();

                }
        }
    }
}
