package com.example.lisap.mynews;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private ViewPager mPager;
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.activity_main_viewpager);
        mPager.setAdapter(mAdapter);

        mTabLayout = findViewById(R.id.activity_main_tablayout);
        mRecyclerView = findViewById(R.id.activity_main_recycler_view);

    }
}
