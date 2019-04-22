package com.example.douyin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MySQLHelper extends SQLiteOpenHelper {
    String information = "create table information (" +
            "id varchar (10), " +
            "password varchar(20)," +
            "sex varchar(10))";
    //数据库第一次被创建时，调用这个方法


    public MySQLHelper(@Nullable Context context,String datebaseName,SQLiteDatabase.CursorFactory factory,int version) {
        //参数二数据库名字
        super(context, datebaseName,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建一个数据库表，id，密码，性别
        db.execSQL(information);//执行一条SQLLite语句
    }

    //当数据库的版本号更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists information");
        onCreate(db);

    }

}
