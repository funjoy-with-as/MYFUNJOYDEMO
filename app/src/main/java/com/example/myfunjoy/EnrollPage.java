package com.example.myfunjoy;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnrollPage extends AppCompatActivity {

    private String account_id;
    private String account_psw;
    private String password_no2;
    private String password_no1;
    EditText et_account_id;
    EditText et_password_no1;
    EditText et_password_no2;

    Button btn_okEnroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_page);
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

                if (!password_no2.equals(password_no1)) {
                    Toast.makeText(EnrollPage.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else if (password_no1.length()<=6 || password_no2.length() <=5) {
                    Toast.makeText(EnrollPage.this, "密码长度不小于6位", Toast.LENGTH_SHORT).show();
                } else if (et_account_id.getText().length() <=0) {
                    Toast.makeText(EnrollPage.this, "账户名为空", Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder builderReSure = new AlertDialog.Builder(EnrollPage.this);
                    builderReSure.setTitle("确认信息");
                    builderReSure.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            account_psw = password_no2;
                            account_id = et_account_id.getText().toString();
                            //传送到数据库

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
        });
    }
    @Override
    public void onBackPressed(){//按返回键则调用该方法
        Intent intent_pressReturn = new Intent();
        intent_pressReturn.putExtra("return_id",account_id);
        intent_pressReturn.putExtra("return_psw",account_psw);
        setResult(RESULT_OK,intent_pressReturn);
        finish();//销毁当前activity
    }
}
