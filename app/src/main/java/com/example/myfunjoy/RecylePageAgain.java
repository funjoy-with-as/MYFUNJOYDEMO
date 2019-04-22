//package com.example.myfunjoy;
//
//import java.util.ArrayList;
//import java.util.List;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.PagerSnapHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.ViewHolder;
//import android.support.v7.widget.SnapHelper;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
////RecyclerView四大组成，LayoutManger，item的布局
////Adapter:item呈现的数据,ItemDecoration:item的分割,Animator:item动画效果
//public class RecylePageAgain extends AppCompatActivity
//{
//    private RecyclerView mRecyclerView;
//    private List<String> mDatas;
//    private HomeAdapter mAdapter;
//    private SnapHelper snapHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recyle_page);
//        initData();
//
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        //布局管理器用于确定RecyclerView中Item的展示方式以及决定何时复用已经不可见的Item，避免重复创建以及执行高成本的findViewById()方法。
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));//必选
//        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画
//
//        //可选
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));//设置分割线
//
//        snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mRecyclerView);
//    }
//
//    protected void initData()
//    {
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }
//    }
//
//
//    //创建Adapter
//    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>//适配器，必须继承RecyclerView.Adapter
//    {
//
//        //创建ViewHolder
//        class MyViewHolder extends RecyclerView.ViewHolder
//        {
//
//            public final TextView tv;
//
//            public MyViewHolder(View view)
//            {
//                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
//            }
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
//        {
//            //LayoutInflater.from指定的写法
//            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(RecylePageAgain.this).inflate(R.layout.mycycleitem, parent,
//                    false));
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position)
//        {
//            holder.tv.setText(mDatas.get(position));
//        }
//
//        @Override
//        public int getItemCount()
//        {
//            return mDatas.size();
//        }
//
//    }
//
//}