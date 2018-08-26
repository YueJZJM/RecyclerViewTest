package com.example.yuejz.recyclerviewtest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.fragment.MultipleFragment;
import com.example.yuejz.recyclerviewtest.fragment.NormalFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int index = getIntent().getIntExtra("position",0);
        Log.d("NormalTextViewHolder", "onClick--> index = " + index);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        updateFragment(index);
    }

    private void updateFragment(int index) {
        switch (index){
            case 0:
                updateNormalFragment(NormalFragment.TYPE_LINEAR_LAYOUT);
                break;
            case 1:
                updateNormalFragment(NormalFragment.TYPE_GRID_LAYOUT);
                break;
            case 2:
                updateNormalFragment(NormalFragment.TYPE_STAGGERED_GRID_LAYOUT);
                break;
            case 3:
                updateMultipleFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
                break;
            case 4:
                updateMultipleFragment(MultipleFragment.TYPE_GRID_LAYOUT);
                break;
            case 5:
                updateMultipleFragment(MultipleFragment.TYPE_STAGGERED_GRID_LAYOUT);
                break;
            default:

        }
    }

    private void updateNormalFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NormalFragment.newInstance(type))
                .commit();
    }

    public void updateMultipleFragment(int type){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MultipleFragment.newInstance(type))
                .commit();
    }
}
