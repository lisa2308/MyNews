package com.example.lisap.mynews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    final int NUMBER_OF_PAGES = 3;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    // ASSOCIATE MOOD WITH ITS POSITION AND COLOR //
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentNews.newInstance(0,0);
            case 1:
                return FragmentNews.newInstance(0,0);
            case 2:
                return FragmentNews.newInstance(0,0);

            default:
                return null;
        }
    }
}