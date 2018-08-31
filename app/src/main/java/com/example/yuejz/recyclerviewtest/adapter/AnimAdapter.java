package com.example.yuejz.recyclerviewtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuejz.recyclerviewtest.R;

import java.util.ArrayList;
import java.util.Collections;

public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.NormalTextViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mTitles = new ArrayList<>();

    public AnimAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public  void addTitles(String[] titles){
        Collections.addAll(mTitles,titles);
        notifyItemRangeInserted(0,titles.length);
    }

    private void addTitle(String title){
        mTitles.add(1,title);
        notifyItemInserted(1);
    }

    public void remove(int position){
        mTitles.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public NormalTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_text,parent,false);
        final NormalTextViewHolder holder =  new NormalTextViewHolder(view,this);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() == 2){
                    remove(holder.getAdapterPosition());
                }else
                    addTitle("test" + holder.getAdapterPosition());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles.get(position));

    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;
        private AnimAdapter mAnimAdapter;

        public NormalTextViewHolder(View itemView,AnimAdapter animAdapter) {
            super(itemView);
            this.mAnimAdapter = animAdapter;
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }

}
