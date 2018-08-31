package com.example.yuejz.recyclerviewtest.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.adapter.HeaderBottomItemAdapter;

import java.security.PublicKey;

public class MultipleHeaderBottomFragment extends Fragment{

    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGRED_GRID_LAYOUT = 2;

    public int type = TYPE_LINEAR_LAYOUT;

    RecyclerView mRecyclerView;

    private GridLayoutManager gridLayoutManager;
    private HeaderBottomItemAdapter mAdapter;

    public static MultipleHeaderBottomFragment newInstance(int type) {
        MultipleHeaderBottomFragment fragment = new MultipleHeaderBottomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            type = getArguments().getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal,container,false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT){
            gridLayoutManager = new GridLayoutManager(getActivity(),2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }else if (type == TYPE_STAGGRED_GRID_LAYOUT){
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        }else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        }
        mAdapter = new HeaderBottomItemAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        if (gridLayoutManager != null){//设置头部及底部View占据整行空间
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                //span size表示一个item的跨度，跨度了多少个span。
                @Override
                public int getSpanSize(int position) {
                    return (mAdapter.isHeaderView(position) || mAdapter.isBottomView(position)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }
}
