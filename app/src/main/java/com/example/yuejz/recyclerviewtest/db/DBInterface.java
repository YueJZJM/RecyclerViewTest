package com.example.yuejz.recyclerviewtest.db;

import android.content.ContentValues;
import android.support.v4.content.CursorLoader;

import java.util.List;

public interface DBInterface<T> {
    //查询
    public T query(String id);
    //删除所有数据
    public int clearAll();
    //批量插入
    public void bulkInsert(List<T> listData);

    public ContentValues getContentValues(T data);

    public CursorLoader getCursorsLoader();
}
