package com.example.root.videodemo.loginAndEnroll;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.videodemo.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class EnrollActivity extends AppCompatActivity {

    private String account_id;
    private String account_psw;
    private String password_no2;
    private String password_no1;
    private String pswQuestion;
    private String pswQuestionAnswer;
    EditText et_account_id;
    EditText et_password_no1;
    EditText et_password_no2;
    EditText et_pswQuestion;
    EditText et_pswQueAnswer;

    Button btn_okEnroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        initSubassembly();//初始化组件
    }

    public void initSubassembly() {
        et_account_id = (EditText) findViewById(R.id.et_register_user_name);
        et_password_no1 = (EditText) findViewById(R.id.et_psw);
        et_password_no2 = (EditText) findViewById(R.id.et_psw_again);

        btn_okEnroll = (Button) findViewById(R.id.btn_register);

        btn_okEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_no1 = et_password_no1.getText().toString();
                password_no2 = et_password_no2.getText().toString();
                account_psw = password_no2;
                account_id = et_account_id.getText().toString();

                BmobQuery<User> bmobQuery = new BmobQuery<>();
                bmobQuery.getObject(account_id, new QueryListener<User>() {
                    @Override
                    public void done(final User informationDB, BmobException e) {
                        if(e == null){
//                            查询到了
                            Toast.makeText(EnrollActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                        }else{
//                            查询不到
                            if (!password_no2.equals(password_no1)) {
                                Toast.makeText(EnrollActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                            } else if (password_no1.length()<=6 || password_no2.length() <=5) {
                                Toast.makeText(EnrollActivity.this, "密码长度不小于6位", Toast.LENGTH_SHORT).show();
                            } else if (et_account_id.getText().length() <=0) {
                                Toast.makeText(EnrollActivity.this, "账户名为空", Toast.LENGTH_SHORT).show();
                            } else {
                                //格式和用户名均输入正确

                                final AlertDialog.Builder builderReSure = new AlertDialog.Builder(EnrollActivity.this);
                                builderReSure.setTitle("确认信息");
                                builderReSure.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        User user = new User();

                                        user.setUsername(account_id);
                                        user.setPassword(password_no1);
                                        user.signUp(new SaveListener<User>() {
                                            @Override
                                            public void done(User user, BmobException e) {
                                                if(e == null){
                                                    //增加成功
                                                    Toast.makeText(EnrollActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }else{
                                                    //增加失败
                                                }
                                            }
                                        });
                                    }
                                });
                                builderReSure.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builderReSure.create();
                                builderReSure.show();
                            }
                        }
                    }
                });

            }
        });
    }
}
