package com.example.lisap.mynews.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.fragments.FragmentNewsViewPager;

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
        mTabLayout.getTabAt(2).setText("ARTS");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.menu_search:
                i = new Intent(this,SearchSettingsActivity.class);
                i.putExtra("isSearch", true);
                startActivity(i);
                return true;
            case R.id.menu_notification:
                i = new Intent(this,SearchSettingsActivity.class);
                i.putExtra("isSearch", false);
                startActivity(i);
                return true;
            case R.id.menu_help:
                i = new Intent(this, HelpActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;

            default:
                return true;
        }
    }
}
