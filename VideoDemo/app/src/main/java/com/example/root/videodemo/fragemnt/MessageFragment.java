package com.example.root.videodemo.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.root.videodemo.R;
import com.example.root.videodemo.activity.MainActivity;
import com.example.root.videodemo.activity.VideoPagerActivity;

public class MessageFragment extends Fragment {
    ImageView mBackImageViewButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        //点击“<”，切换界面
        mBackImageViewButton = (ImageView)view.findViewById(R.id.back_image_button);
        mBackImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
