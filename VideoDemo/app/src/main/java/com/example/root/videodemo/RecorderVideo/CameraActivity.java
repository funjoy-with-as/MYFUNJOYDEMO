package com.example.root.videodemo.RecorderVideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cktim.camera2library.Camera2Config;
import com.cktim.camera2library.camera.Camera2RecordActivity;
import com.example.root.videodemo.R;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOpenCamera2;
    private Button btnLocalVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        //配置Camera2相关参数，
        Camera2Config.RECORD_MAX_TIME = 10;//录制总时间长度
        Camera2Config.RECORD_MIN_TIME = 2;//最小录制时长
        Camera2Config.RECORD_PROGRESS_VIEW_COLOR = R.color.colorAccent;//进度条颜色，默认蓝色
        Camera2Config.PREVIEW_MAX_HEIGHT = 1300;//最大高度预览尺寸
        Camera2Config.PATH_SAVE_VIDEO = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CameraV2222/";//小视频存放地址，不设置的话默认在根目录的Camera2文件夹
//        Camera2Config.PATH_SAVE_PIC = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CameraV2222/CameraV22222222/";//图片保存地址，不设置的话默认在根目录的Camera2文件夹
        Camera2Config.ENABLE_RECORD = true;//录制功能
        Camera2Config.ENABLE_CAPTURE = false;//拍照功能

        Camera2Config.ACTIVITY_AFTER_CAPTURE = Camera2RecordFinishActivity.class;//拍照完成后需要跳转的Activity,一般这个activity做处理照片或者视频用

        btnLocalVideo = (Button) findViewById(R.id.btn_locatVideo);
        btnOpenCamera2 = (Button) findViewById(R.id.btn_openCamera2);
        btnOpenCamera2.setOnClickListener(this);
        btnLocalVideo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openCamera2: {
                Intent intent = new Intent(CameraActivity.this, Camera2RecordActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.btn_locatVideo: {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
                break;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Intent intent = new Intent(CameraActivity.this, UploadActivity.class);
            intent.putExtra("path",uri.toString());
            Log.i("你好","+++++++++++++++++++++++"+uri.toString());
            startActivity(intent);
            finish();
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }
}
