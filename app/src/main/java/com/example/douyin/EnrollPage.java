package com.example.douyin;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnrollPage extends AppCompatActivity {

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
    RadioGroup rg_sex;
    private String sex;

    Button btn_okEnroll;

    MySQLHelper helper;
    SQLiteDatabase db;
    ContentValues values;


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
//        et_pswQueAnswer = (EditText) findViewById(R.id.et_pswAnswerFind);
//        et_pswQuestion = (EditText) findViewById(R.id.et_pswFindQue);
//        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);

        btn_okEnroll = (Button) findViewById(R.id.btn_register);

        btn_okEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_no1 = et_password_no1.getText().toString();
                password_no2 = et_password_no2.getText().toString();
                account_psw = password_no2;
                account_id = et_account_id.getText().toString();

                helper = new MySQLHelper(EnrollPage.this,"information",null,1);//创建一个数据库
                db = helper.getReadableDatabase();
                Cursor cursor = db.query("information",null,"id='"+account_id+"'",null,null,null,null);
                if(cursor.getCount() > 0){
                    Toast.makeText(EnrollPage.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                } else if (!password_no2.equals(password_no1)) {
                    Toast.makeText(EnrollPage.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else if (password_no1.length()<=6 || password_no2.length() <=5) {
                    Toast.makeText(EnrollPage.this, "密码长度不小于6位", Toast.LENGTH_SHORT).show();
                } else if (et_account_id.getText().length() <=0) {
                    Toast.makeText(EnrollPage.this, "账户名为空", Toast.LENGTH_SHORT).show();
                }
//                else if(et_pswQuestion.getText().length() <=0 || et_pswQueAnswer.getText().length() <= 0){
//                    Toast.makeText(EnrollPage.this,"输入密保问题和答案",Toast.LENGTH_SHORT).show();
//                }
                else {

                    final AlertDialog.Builder builderReSure = new AlertDialog.Builder(EnrollPage.this);
                    builderReSure.setTitle("确认信息");
                    builderReSure.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            pswQuestion = et_pswQuestion.getText().toString();
//                            pswQuestionAnswer = et_pswQueAnswer.getText().toString();

                            db = helper.getWritableDatabase();
                            values = new ContentValues();
                            Cursor cursor = db.query("information",null,"id='"+et_account_id+"'",null,null,null,null);

                                values.put("id", account_id);
                                values.put("password", account_psw);
                                values.put("sex", "none");
//                                values.put("pswQue",pswQuestion);
//                                values.put("pswQueAnser",pswQuestionAnswer);
                                db.insert("information", null, values);
                                //表的名称，将要插入的行为空所放置的，ContentValues对象.
                                Toast.makeText(EnrollPage.this, "注册成功", Toast.LENGTH_SHORT).show();
                            db.close();
                            finish();
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
}
