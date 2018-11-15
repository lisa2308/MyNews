package com.example.lisap.mynews;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lisap.mynews.entities.Result;
import android.support.v7.widget.RecyclerView;
public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private ViewPager mPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result result = new Result();
        result.getStatus();
        result.getResponse().getMeta().getHits();







        //CREATE ADAPTER FOR VIEWPAGER//
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.activity_main_viewpager);
        mPager.setAdapter(mAdapter);

        //TABLAYOUT CREATION//
        mTabLayout = findViewById(R.id.activity_main_tablayout);
        //ASSOCIATE VIEWPAGER WITH TABLAYOUT//
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.getTabAt(0).setText("1");
        mTabLayout.getTabAt(1).setText("2");
        mTabLayout.getTabAt(2).setText("3");



    }
}
