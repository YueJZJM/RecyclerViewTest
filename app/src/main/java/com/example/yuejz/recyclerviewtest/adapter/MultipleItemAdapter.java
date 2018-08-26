package com.example.yuejz.recyclerviewtest.adapter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.util.LogUtil;

public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum ITEM_TYPE{
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }
    private final static String Tag = "MultipleItemAdapter";
    private String[] mTiltle;
    private LayoutInflater mLayoutInflaterl;

    private final Context mContext;

    public MultipleItemAdapter(Context context){
        this.mContext = context;
        this.mTiltle = context.getResources().getStringArray(R.array.titles);
        mLayoutInflaterl = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            final  ImageViewHolder imageViewHolder = new ImageViewHolder(mLayoutInflaterl.inflate(R.layout.item_image, parent, false), this);
            imageViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(Tag,imageViewHolder.mTextView.getText().toString());
                }
            });
            return imageViewHolder;
        }
        else {
            final TextViewHolder textViewHolder = new TextViewHolder(mLayoutInflaterl.inflate(R.layout.item_text, parent, false),this);
            textViewHolder.mTetxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(Tag,textViewHolder.mTetxView.getText().toString());
                }
            });
            return textViewHolder;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder)
            ((TextViewHolder) holder).mTetxView.setText(mTiltle[position]);
        else if (holder instanceof ImageViewHolder)
            ((ImageViewHolder) holder).mTextView.setText(mTiltle[position]);
    }

    @Override
    public int getItemViewType(int position) {
        //返回枚举的序列
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() :ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mTiltle.length;
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView mTetxView;
        private final MultipleItemAdapter adapter;

        TextViewHolder(View view,MultipleItemAdapter adapter){
            super(view);
            this.mTetxView = view.findViewById(R.id.text_view);
            this.adapter = adapter;
        }
    }

     static class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;

        private TextView mTextView;

        private MultipleItemAdapter mMultipleItemAdapter;

         ImageViewHolder(View view,MultipleItemAdapter adapter){
             super(view);
            this.mImageView = view.findViewById(R.id.image_image_view);
            this.mTextView = view.findViewById(R.id.image_text_view);
            this.mMultipleItemAdapter = adapter;
        }
    }

}
