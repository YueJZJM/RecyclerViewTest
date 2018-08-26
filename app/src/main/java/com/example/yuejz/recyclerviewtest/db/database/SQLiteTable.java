package com.example.yuejz.recyclerviewtest.db.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteTable {
    String mTableName;

    ArrayList<Column> mColumnsDefinittions = new ArrayList<>();

    public String getmTableName(){return mTableName;}

    public SQLiteTable(String tableName){
        mTableName = tableName;
        mColumnsDefinittions.add(new Column(BaseColumns._ID,Column.Constraint.PRIMARY_KEY,
                Column.DataType.INTEGER));
    }

    public SQLiteTable addColumn(Column column){
        mColumnsDefinittions.add(column);
        return this;
    }

    public SQLiteTable addColumn(String columnName,Column.DataType dataType){
        mColumnsDefinittions.add(new Column(columnName,null,dataType));
        return this;
    }

    public void create(SQLiteDatabase db){
        String formatter = " %s";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(mTableName);
        stringBuilder.append("(");
        int columnCount = mColumnsDefinittions.size();
        int index = 0;
        for (Column columnsDefinition : mColumnsDefinittions) {
            stringBuilder.append(columnsDefinition.getmColumnName()).append(
                    String.format(formatter, columnsDefinition.getmDataType().name()));
            Column.Constraint constraint = columnsDefinition.getmConstraint();

            if (constraint != null) {
                stringBuilder.append(String.format(formatter, constraint.toString()));
            }
            if (index < columnCount - 1) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append(");");
        Log.d("sql",stringBuilder.toString());
        db.execSQL(stringBuilder.toString());
    }
    public void delete(final SQLiteDatabase database){
        database.execSQL("DROP TABLE IF EXISTS " + mTableName);
    }
}
