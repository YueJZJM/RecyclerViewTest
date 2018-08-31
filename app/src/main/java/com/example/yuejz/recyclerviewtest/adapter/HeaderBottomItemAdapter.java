package com.example.yuejz.recyclerviewtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.util.LogUtil;

public class HeaderBottomItemAdapter extends  BaseMultipleItemAdapter{

    private String [] mTitles;

    private final  String TAG = "HeaderBottomItemAdapter";

    public  HeaderBottomItemAdapter(Context context) {
        super(context);
        mTitles = context.getResources().getStringArray(R.array.titles);
        mHeaderCount = 1;
        mBottomCount = 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderView(ViewGroup parent) {
        final HeaderViewHolder holder =  new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_image,parent,false)) ;
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG,holder.mTextView.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentView(ViewGroup parent) {
        final  ContentViewHolder holder = new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_text,parent,false)) ;
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG,holder.mTextView.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent) {
        final BottomViewHolder holder = new BottomViewHolder(mLayoutInflater.inflate(R.layout.item_image,parent,false)) ;
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG,holder.mTextView.getText().toString());
            }
        });
        return holder;

    }

    @Override
    public int getContentItemCount() {
        return 4;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder)
            ((HeaderViewHolder) holder).mTextView.setText(mTitles[0]);
        else if (holder instanceof ContentViewHolder)
            ((ContentViewHolder) holder).mTextView.setText(mTitles[position - mHeaderCount]);
        else if (holder instanceof BottomViewHolder)
            ((BottomViewHolder) holder).mTextView.setVisibility(View.GONE);
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_image_view);
            mTextView = itemView.findViewById(R.id.image_text_view);
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;

        public BottomViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_image_view);
            mTextView = itemView.findViewById(R.id.image_text_view);
        }
    }

}
