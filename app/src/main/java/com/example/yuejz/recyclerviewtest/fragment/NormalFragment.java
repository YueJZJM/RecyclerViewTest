package com.example.yuejz.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.adapter.NormalRecyclerViewAdapter;

public class NormalFragment extends Fragment {
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    private RecyclerView mRecyclerView;

    private int type = TYPE_LINEAR_LAYOUT;

    public static NormalFragment newInstance(int type){
        NormalFragment fragment =new NormalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            type = getArguments().getInt("type", TYPE_LINEAR_LAYOUT);
            Log.d("type",type+"");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT) {
            //这里用线性宫格显示 类似于grid view
            GridLayoutManager gridLayoutManager =  new GridLayoutManager(getActivity(), 2);
            //https://www.jianshu.com/p/675883c26ef2

//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
//
//                @Override
//                public int getSpanSize(int position) {
//                    return 0;
//                }
//            });
            mRecyclerView.setLayoutManager(gridLayoutManager);


        }
        else if (type == TYPE_STAGGERED_GRID_LAYOUT)
            //这里用线性宫格显示 类似于瀑布流
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        else
            //这里用线性显示 类似于list view
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(getActivity()));
        //RecyclerView.ItemAnimator主要用于RecyclerView的Item添加、移除、更新时的动画。这里设置的默认的，并没有什么用
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 如果单个item显示的字数大于指定某个值就显示一列  默认2列
     */

    //设置item数据大于多少字只显示一行  默认 超过九个字的程度只显示一列

}
