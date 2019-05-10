package com.example.root.videodemo.RecorderVideo;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.root.videodemo.MainActivity;
import com.example.root.videodemo.R;
import com.example.root.videodemo.upload.Push;

import java.io.File;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton ibtn_ok;
    private ImageButton ibtn_cancel;
    private VideoView videoView;

    String mPath;

    private MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);
        ibtn_ok = (ImageButton)findViewById(R.id.ibtn_sureSelect);
        videoView = (VideoView) findViewById(R.id.vv_showVideo);
        ibtn_cancel = (ImageButton)findViewById(R.id.ibtn_cancel);
        ibtn_cancel.setOnClickListener(this);
        ibtn_ok.setOnClickListener(this);
        videoView.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        mPath = intent.getStringExtra("path");
        Log.i("视频",mPath);
        videoView.setVideoPath(mPath);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_cancel:{
                Intent intent = new Intent(UploadActivity.this,CameraActivity.class);
                startActivity(intent);
                finish();
            }
            case R.id.ibtn_sureSelect:{
                AlertDialog dialog = new AlertDialog.Builder(UploadActivity.this)
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
//                                final BmobFile bmobFile = new BmobFile(new File(mPath));
//                                bmobFile.uploadblock(new UploadFileListener() {
//                                    @Override
//                                    public void onProgress(Integer value){
//                                        //长传进度

//                                    }
//                                    @Override
//                                    public void done(BmobException e) {
//                                        if(e==null){
//                                            progressDialog.dismiss();
//                                            Toast.makeText(UploadActivity.this,"上传成功", Toast.LENGTH_LONG).show();
//                                        }else{
//                                            Toast.makeText(UploadActivity.this,"上传失败,错误"+e.toString(), Toast.LENGTH_LONG).show();
//                                            Log.i("错误",e.toString());
//                                        }
//                                    }
//                                });
                                //上传到服务器
//                                ProgressDialog progressDialog = new ProgressDialog(UploadActivity.this);
//                                progressDialog.setTitle("正在上传");
////                                progressDialog.setTitle(mPath);
//                                progressDialog.setIcon(R.drawable.bmob_update_button_cancel_bg_focused);
//                                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                                progressDialog.show();
                                try {
                                    Push push = new Push();
                                    push.setFile(mPath);
                                    push.start();
                                    Toast.makeText(UploadActivity.this,"上传成功",Toast.LENGTH_LONG).show();
                                } catch (Throwable e){
                                    Toast.makeText(UploadActivity.this,"上传失败",Toast.LENGTH_LONG).show();
                                }
//                                progressDialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        }

    }

}
