package com.example.root.videodemo.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.root.videodemo.R;
import com.example.root.videodemo.activity.VideoPagerActivity;

public class FellowFragment extends Fragment {
    ImageView mBackButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fellow, container, false);

        //点击“<”，切换界面
        mBackButton = (ImageView)view.findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), VideoPagerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
