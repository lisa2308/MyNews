package com.example.lisap.mynews;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentNewsViewPager mAdapter;
    private ViewPager mPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREATE ADAPTER FOR VIEWPAGER//
        mAdapter = new FragmentNewsViewPager(getSupportFragmentManager());
        mPager = findViewById(R.id.activity_main_viewpager);
        mPager.setAdapter(mAdapter);

        //TABLAYOUT CREATION//
        mTabLayout = findViewById(R.id.activity_main_tablayout);
        //ASSOCIATE VIEWPAGER WITH TABLAYOUT//
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.getTabAt(0).setText("TOP STORIES");
        mTabLayout.getTabAt(1).setText("MOST POPULAR");
        mTabLayout.getTabAt(2).setText("BUISNESS");
    }
}
