package com.example.root.videodemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.videodemo.fragemnt.SelfFragment;
import com.example.root.videodemo.loginAndEnroll.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class EditActivity extends AppCompatActivity {
    EditText etName;
    EditText etPassword;
    Button btSaved;
    Button btBack;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("INFOR", Context.MODE_PRIVATE);


        //用户名
        etName = (EditText)findViewById(R.id.edit_name);
        etName.setText(sharedPreferences.getString("USERNAME",""));


        //密码
        etPassword =(EditText)findViewById(R.id.edit_password);
        etPassword.setText(sharedPreferences.getString("USERPASSWORD", ""));

        //保存按钮
        btSaved = (Button)findViewById(R.id.save_button);
        btSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        //返回按钮
//        btBack = (Button)findViewById(R.id.back_button);
//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //点击返回按钮，返回“我”界面
////                MainActivity activity = new MainActivity();
////                activity.setFragment(new SelfFragment());
//                Intent intent  = new Intent(EditActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


    }

    private void update() {
        String account_id = etName.getText().toString();
        String account_password = etPassword.getText().toString();

        //修改本机存储的数据
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", account_id);
        editor.putString("USERPASSWORD", account_password);
        editor.commit(); //提交修改


        //修改云数据
        User user = new User();
        user.setUsername(account_id);
        user.setPassword(account_password);
        user.update(sharedPreferences.getString("OBJECTID", ""), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(EditActivity.this,"保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
