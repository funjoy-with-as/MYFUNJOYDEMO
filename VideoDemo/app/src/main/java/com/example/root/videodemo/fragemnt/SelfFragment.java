package com.example.root.videodemo.fragemnt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.videodemo.EditActivity;
import com.example.root.videodemo.MainActivity;
import com.example.root.videodemo.R;
import com.example.root.videodemo.loginAndEnroll.LoginActivity;

public class SelfFragment extends android.support.v4.app.Fragment {
    TextView tvName;
    Button btLogout;
    Button btEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_self, container, false);
        tvName  = (TextView)view.findViewById(R.id.tv_name);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("INFOR", Context.MODE_PRIVATE);
        tvName.setText(sharedPreferences.getString("USERNAME", ""));
        String str = tvName.getText().toString();
        Log.i("name",str);

        btLogout = (Button)view.findViewById(R.id.logout_button);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(), "退出登录成功",Toast.LENGTH_SHORT).show();
            }
        });

        btEdit = (Button)view.findViewById(R.id.edit_button);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击编辑按钮，启动编辑界面
//                Intent intent  = new Intent(getActivity(), EditActivity.class);
//                startActivity(intent);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.buttonLayout, new EditInfoFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        init();
        return view;
    }


    public void init(){

    }

}
