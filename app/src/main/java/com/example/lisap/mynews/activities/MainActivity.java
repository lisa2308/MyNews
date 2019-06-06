package com.example.lisap.mynews.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.fragments.FragmentNewsViewPager;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private FragmentNewsViewPager mAdapter;
    private ViewPager mPager;
    private TabLayout mTabLayout;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setup toolbar//
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_24dp);

        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        mNavigationView = findViewById(R.id.activity_main_nav_view);

        mNavigationView.setNavigationItemSelectedListener(this);


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

    //Click Listener menu item//
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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return true;
        }
    }

    //Click Listener menu navigation drawer//
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
        switch (item.getItemId()){
            case R.id.activity_main_drawer_arts:
                i.putExtra("q", "sports");
                i.putExtra("fq", "sports");
                startActivity(i);
                return true;
            case R.id.activity_main_drawer_business:
                i.putExtra("q", "business");
                i.putExtra("fq", "business");
                startActivity(i);
                return true;
            case R.id.activity_main_drawer_entrepreneurs:
                i.putExtra("q", "entrepreneurs");
                i.putExtra("fq", "entrepreneurs");
                startActivity(i);
                return true;
            case R.id.activity_main_drawer_politics:
                i.putExtra("q", "politics");
                i.putExtra("fq", "politics");
                startActivity(i);
                return true;
            case R.id.activity_main_drawer_sports:
                i.putExtra("q", "sports");
                i.putExtra("fq", "sports");
                startActivity(i);
                return true;
            case R.id.activity_main_drawer_travel:
                i.putExtra("q", "travel");
                i.putExtra("fq", "travel");
                startActivity(i);
                return true;

            default:
                return true;
        }

    }
}
