package com.example.yuejz.recyclerviewtest.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.adapter.ItemsAdapter;
import com.example.yuejz.recyclerviewtest.bean.DemoItem;
import com.example.yuejz.recyclerviewtest.db.ItemsDataHelper;
import com.example.yuejz.recyclerviewtest.db.database.DataProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
   //@BindView( R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ItemsDataHelper mDataHelper;
    private ItemsAdapter mAdapter;
   // private Unbinder unbinder;
    public static ItemFragment newInstance() {
      //  Bundle args = new Bundle();
        ItemFragment fragment = new ItemFragment();
      //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataHelper = new ItemsDataHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal,container,false);
       // unbinder = ButterKnife.bind(this,rootView);
       // mRecyclerView = inflater.inflate(R.id.recycler_view,container);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ItemsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //使用Loader异步加载数据
        getLoaderManager().initLoader(0, null, this);
    }

    /**
     * cursorloader的异步操作
     这里我也不多讲，只要大家记住几点：
     1. 实现LoaderManager.LoaderCallbacks类，T是代表你希望返回的数据是咋样的，可以是string，boolean等基本数据类型，也可以是cursor等等。
     2. 当cursorLoader被初始化之后，会首先执行onCreateLoader()方法，执行完之后，会返回T类型的数据。
     3. 当onCreateLoader()方法执行完毕，就该执行onLoadFinished()方法了，在这里你就可以进行数据的获取了。
     4. 其它的一些方法，比如loader对象被重置了，就会执行onLoaderReset()方法。
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mDataHelper.getCursorsLoader();
    }

    /**
     * 开始是插入数据
     * @param loader
     * @param cursor
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            loadItems();
           // Log.d("loadItems","loadItems");
        } else {
            mAdapter.changeCursor(cursor);
           // Log.d("else","loadItems");
        }
    }


    private void loadItems() {
        String[] items = getActivity().getResources().getStringArray(R.array.items);
        ArrayList<DemoItem> demoItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            demoItems.add(new DemoItem(i, items[i]));
        }
        mDataHelper.bulkInsert(demoItems);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      //  unbinder.unbind();
    }

}
