package com.example.yuejz.recyclerviewtest.adapter;

import android.database.Cursor;
import android.widget.Filter;

public class CursorFilter extends Filter {
    CursorFilterClient mClient;
    interface CursorFilterClient{
        CharSequence convertToString(Cursor cursor);
        Cursor runQueryOnBackgroundThread(CharSequence constraint);
        Cursor getCursor();
        void changeCursor(Cursor cursor);
    }

    CursorFilter(CursorFilterClient client) {
        mClient = client;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return mClient.convertToString((Cursor) resultValue);
    }

    /**
     * 查询数据
     * @param constraint
     * @return
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        Cursor cursor = mClient.runQueryOnBackgroundThread(constraint);

        FilterResults results = new FilterResults();
        if (cursor != null){
            results.count = cursor.getCount();
            results.values = cursor;
        }else {
            results.count = 0;
            results.values = null;
        }
        return results;
    }

    /**
     * 刷新数据
     * @param constraint
     * @param results
     */
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        Cursor oldCursor = mClient.getCursor();
        if (results.values != null && results.values != oldCursor){
            mClient.changeCursor((Cursor) results.values);
        }
    }
}
