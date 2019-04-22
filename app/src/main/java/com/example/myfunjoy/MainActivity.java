package com.example.myfunjoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent intent_home = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent_home);
                    return true;
                case R.id.navigation_concerned:
                    mTextMessage.setText(R.string.title_concerned);
                    Intent intent_login = new Intent(MainActivity.this,LoginPage.class);
                    startActivity(intent_login);
                    return true;
                case R.id.navigation_message:
                    mTextMessage.setText(R.string.title_message);
                    Intent intent_listVideo = new Intent(MainActivity.this,RecylePageAgain.class);
                    startActivity(intent_listVideo);
                    return true;
                case R.id.navigation_add:
                    mTextMessage.setText(R.string.title_add);
                    Intent intent_test = new Intent(MainActivity.this,RecylePage.class);
                    startActivity(intent_test);
                    return true;
            }
            return false;
        }
    };



}
