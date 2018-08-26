package com.example.yuejz.recyclerviewtest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuejz.recyclerviewtest.R;
import com.example.yuejz.recyclerviewtest.fragment.ItemFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ItemFragment.newInstance())
                    .commit();
        }
    }
}
