package com.example.douyin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LoginPage extends AppCompatActivity implements
        View.OnClickListener{

    private Button btnLogin;
    String userName = null;
    private String psw = null;//用户名，密码，加密密码
    private EditText et_user_name,etPsw;//用户名，密码
    private TextView tv_register,tv_find_psw;//注册账号和找回密码
    private ToggleButton tb_isShowpsw;


    MySQLHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        init();
    }


    private void init() {//初始化各个控件
        btnLogin = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        etPsw = (EditText) findViewById(R.id.et_password);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tb_isShowpsw = (ToggleButton) findViewById(R.id.tb_isShowPsw);

        btnLogin.setOnClickListener(this);
        tv_find_psw.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                userName = et_user_name.getText().toString();
                psw = etPsw.getText().toString();
                helper = new MySQLHelper(LoginPage.this,"information",null,1);//创建一个数据库
                db = helper.getReadableDatabase();
                //利用游标遍历所有对象
                Cursor cursor = db.query("information",null,"id='"+userName+"'",null,null,null,null);
                cursor.moveToFirst();
                if(cursor.getCount() == 0){
                    //找不到
                    Toast.makeText(LoginPage.this,"用户不存在",Toast.LENGTH_SHORT).show();
                }else{
                    String password = cursor.getString(1);
                    if(password.equals(psw)){
                        Toast.makeText(LoginPage.this,"登录成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(LoginPage.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
                db.close();
                break;
            }
            case R.id.tv_register:{
                Intent intent = new Intent(LoginPage.this,EnrollPage.class);
                startActivityForResult(intent,1);
                break;
            }
            case R.id.tv_find_psw:{
                break;
            }
        }

    }
}
