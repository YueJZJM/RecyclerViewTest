package com.example.yuejz.recyclerviewtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuejz.recyclerviewtest.R;

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {

   // private final LayoutInflater mLayoutInflater;
    private String[] mTitles;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NormalTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final NormalTextViewHolder holder =  new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text,parent,false));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,holder.mTextView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
    }



    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }
}
