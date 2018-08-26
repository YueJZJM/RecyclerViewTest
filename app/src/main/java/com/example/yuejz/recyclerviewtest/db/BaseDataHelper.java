package com.example.yuejz.recyclerviewtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public abstract class BaseDataHelper {
    private Context mContext;
    protected abstract Uri getContentUri();
    protected abstract String getTableName();
    public Context getContext() {
        return mContext;
    }
    public BaseDataHelper(Context context){
        mContext = context;
    }

    public void notifyChange(){
        mContext.getContentResolver().notifyChange(getContentUri(),null);
    }

    protected final Cursor query(Uri uri,String[] projection,String selection,
                                 String[] selectionArgs, String sortOrder){
        return mContext.getContentResolver().query(uri,projection,selection,selectionArgs,sortOrder);
    }

    protected final Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Uri insert(ContentValues values){
        return mContext.getContentResolver().insert(getContentUri(),values);
    }

    protected final int bulkInsert(ContentValues[] values){
        return mContext.getContentResolver().bulkInsert(getContentUri(),values);
    }
}
