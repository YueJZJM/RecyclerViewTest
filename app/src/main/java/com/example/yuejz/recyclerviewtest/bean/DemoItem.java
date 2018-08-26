package com.example.yuejz.recyclerviewtest.bean;

import android.database.Cursor;
import android.util.Log;

import com.example.yuejz.recyclerviewtest.db.ItemsDataHelper;

public class DemoItem {
    public int id;
    public String title;

    public DemoItem() {
    }

    public DemoItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static DemoItem fromCursor(Cursor cursor) {
        DemoItem demoItem = new DemoItem();
        demoItem.id = cursor.getInt(cursor.getColumnIndex(ItemsDataHelper.ItemDBInfo.ID));
        demoItem.title = cursor.getString(cursor.getColumnIndex(ItemsDataHelper.ItemDBInfo.TITLE));
       // Log.d("demoItem",demoItem.toString());
        return demoItem;
    }

    @Override
    public String toString() {
        return "DemoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
