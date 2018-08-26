package com.example.yuejz.recyclerviewtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuejz.recyclerviewtest.ui.DetailActivity;
import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.ui.SelectActivity;
import com.example.yuejz.recyclerviewtest.bean.DemoItem;

public class ItemsAdapter extends BaseAbstractRecycleCursorAdapter<RecyclerView.ViewHolder>{

    private final LayoutInflater mLayoutInflater;

    public ItemsAdapter(Context context){
        super(context,null);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        DemoItem item = DemoItem.fromCursor(cursor);
//        Log.d("item",item.title);
        ((NormalTextViewHolder) holder).mTextView.setText(item.title);
    }

    @NonNull
    @Override
    public NormalTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mLayoutInflater.inflate(R.layout.item_text,parent,false);
        final NormalTextViewHolder holder = new NormalTextViewHolder(view,this);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.d("NormalTextViewHolder", "onClick--> position = " + position);
                DemoItem item = DemoItem.fromCursor((Cursor) holder.mAdapter.getItem(position));
                if (position < 11) {
                    Intent intent = new Intent(holder.mAdapter.mContext, DetailActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("title", item.title);
                    holder.mAdapter.mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(holder.mAdapter.mContext, SelectActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("title", item.title);
                    holder.mAdapter.mContext.startActivity(intent);
                }
            }
        });

        return holder;
    }

    /**
     *
     */
    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        ItemsAdapter mAdapter;

        NormalTextViewHolder(View view, ItemsAdapter adapter) {
            super(view);
            mTextView = view.findViewById(R.id.text_view);
            mAdapter = adapter;
         //   ButterKnife.bind(this, view);
        }



//        @OnClick(R.id.cv_item)
//        void onTtemClick(){
//            Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
//            DemoItem item = DemoItem.fromCursor((Cursor) mAdapter.getItem(getPosition()));
//            if (getPosition() < 11) {
//                Intent intent = new Intent(mAdapter.mContext, DetailActivity.class);
//                intent.putExtra("position", getPosition());
//                intent.putExtra("title", item.title);
//                mAdapter.mContext.startActivity(intent);
//            } else {
//                Intent intent = new Intent(mAdapter.mContext, SelectActivity.class);
//                intent.putExtra("position", getPosition());
//                intent.putExtra("title", item.title);
//                mAdapter.mContext.startActivity(intent);
//            }
//        }

    }
}
