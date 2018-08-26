package com.example.yuejz.recyclerviewtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.example.yuejz.recyclerviewtest.bean.DemoItem;
import com.example.yuejz.recyclerviewtest.db.database.Column;
import com.example.yuejz.recyclerviewtest.db.database.DataProvider;
import com.example.yuejz.recyclerviewtest.db.database.SQLiteTable;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataHelper extends BaseDataHelper implements DBInterface<DemoItem>{

    @Override
    protected String getTableName() {
        return ItemDBInfo.TABLE_NAME;
    }


    public ItemsDataHelper(Context context){
        super(context);
    }




    @Override
    public DemoItem query(String id) {
        DemoItem item = null;
        Cursor cursor;
        cursor =query(null,ItemDBInfo.ID + "=?",
                new String[]{id},null);
        if (cursor.moveToFirst()){
            item = DemoItem.fromCursor(cursor);
        }
        cursor.close();
        return item;
    }

    @Override
    public int clearAll() {
        return 0;
    }

    @Override
    public void bulkInsert(List<DemoItem> listData) {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (DemoItem item : listData) {
            ContentValues values = getContentValues(item);
            contentValues.add(values);
         //   Log.d("array",contentValues.toString());
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
      // Log.d("array",String.valueOf(listData.size()));
    }

    @Override
    public ContentValues getContentValues(DemoItem data) {
        ContentValues values = new ContentValues();
        values.put(ItemDBInfo.ID, data.id);
        values.put(ItemDBInfo.TITLE, data.title);
        return values;
    }

    @Override
    public CursorLoader getCursorsLoader() {

        return new CursorLoader(getContext(), getContentUri(), null, null, null,ItemDBInfo.ID + " ASC");
    }
    @Override
    protected Uri getContentUri() {
        return DataProvider.ALL_ITEMS_CONTENT_URI;
    }


    public static final class ItemDBInfo implements BaseColumns{
        private  ItemDBInfo(){}

        public static final String TABLE_NAME = "items";
        public static final String ID = "id";
        public static final String TITLE = "title";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(TITLE,Column.DataType.TEXT);
    }




}
