package com.example.yuejz.recyclerviewtest.fragment;

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
import com.example.yuejz.recyclerviewtest.adapter.MultipleItemAdapter;

public class MultipleFragment extends Fragment{
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    private RecyclerView mRecyclerView;
    private int type = TYPE_LINEAR_LAYOUT;
   // private int type = TYPE_LINEAR_LAYOUT;
    public static MultipleFragment newInstance(int type) {
        MultipleFragment fragment = new MultipleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            type =  getArguments().getInt("type",TYPE_LINEAR_LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_normal,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT)
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        else if (type == TYPE_STAGGERED_GRID_LAYOUT)
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        else
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new MultipleItemAdapter(getActivity()));
    }
}
