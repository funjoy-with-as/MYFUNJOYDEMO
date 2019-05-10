package com.example.root.videodemo.loginAndEnroll;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.root.videodemo.MainActivity;
import com.example.root.videodemo.R;
import com.example.root.videodemo.fragemnt.VideoFragment;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener{

    private Button btnLogin;
    String userName = null;
    private String psw = null;//用户名，密码，加密密码
    private EditText et_user_name,etPsw;//用户名，密码
    private TextView tv_register,tv_find_psw;//注册账号和找回密码
    private ToggleButton tb_isShowpsw;
    public Boolean isLogined;//是否登录

    public ConstraintLayout mConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "d4e343686d1f62f8ceee34d692c0c83b");

        init();
    }


    private void init() {
//        //设置layout透明度
//        mConstraintLayout = (ConstraintLayout)findViewById(R.id.backgroundLayout);
//        mConstraintLayout.getBackground().mutate().setAlpha(150);

        //初始化各个控件
        btnLogin = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        etPsw = (EditText) findViewById(R.id.et_password);
//        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tb_isShowpsw = (ToggleButton) findViewById(R.id.tb_isShowPsw);

        btnLogin.setOnClickListener(this);
//        tv_find_psw.setOnClickListener(this);
        tv_register.setOnClickListener(this);

        tb_isShowpsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    etPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    etPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("INFOR", Context.MODE_PRIVATE);
        et_user_name.setText(sharedPreferences.getString("USERNAME",""));
        etPsw.setText(sharedPreferences.getString("USERPASSWORD",""));

    }

    private void btnLoginClicked(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                userName = et_user_name.getText().toString();
                psw = etPsw.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

                progressDialog.setTitle("正在登录");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();


                final User user = new User();
                user.setUsername(userName);
                user.setPassword(psw);
                user.login(new SaveListener<User>() {
                    public void done(User object, BmobException e) {
                        if(e==null){
                                Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                                //保存数据到本地,使用SharedPreferences
                                //存入数据
                                SharedPreferences sharedPreferences = getSharedPreferences("INFOR", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString("USERNAME",userName);
                                edit.putString("USERPASSWORD",psw);
                                edit.putString("OBJECTID", user.getObjectId());
                                edit.commit();


                            Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(e != null){
                            Toast.makeText(LoginActivity.this,"用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
            break;
            case R.id.tv_register:{
                Intent intent = new Intent(LoginActivity.this,EnrollActivity.class);
                startActivityForResult(intent,1);
                break;
            }
//            case R.id.tv_find_psw:{
//                break;
//            }
        }

    }
}
