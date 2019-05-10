package com.example.root.videodemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.videodemo.RecorderVideo.CameraActivity;
import com.example.root.videodemo.fragemnt.SelfFragment;
import com.example.root.videodemo.fragemnt.VideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FragmentManager fragmentManager;//fragment管理器
    FragmentTransaction fragmentTransaction;//事物管理器
    TextView tvHome,tvConcerned,tvMessagee,tvMine;
    ImageView ivAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    public void initComponent(){
        tvHome = (TextView) findViewById(R.id.navigationBar_home);
        tvConcerned = (TextView) findViewById(R.id.navigationBar_concerned);
        ivAdd = (ImageView) findViewById(R.id.navigationBar_add);
        tvMessagee = (TextView) findViewById(R.id.navigationBar_messages);
        tvMine = (TextView) findViewById(R.id.navigationBar_mine);
        tvHome.setOnClickListener(this);
        tvConcerned.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvMessagee.setOnClickListener(this);
        tvMine.setOnClickListener(this);
        setFragment(new VideoFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigationBar_home:
                setFragment(new VideoFragment());
                return;
            case R.id.navigationBar_add:
                Intent intent  = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                return;
            case R.id.navigationBar_mine:
                setFragment(new SelfFragment());
                return;
        }
    }

    //创建fragment
    public void setFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
