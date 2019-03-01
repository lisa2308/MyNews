package com.example.lisap.mynews.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentNewsViewPager extends FragmentPagerAdapter {
    final int NUMBER_OF_PAGES = 3;

    public FragmentNewsViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    //PAGES CREATION//
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentNews.newInstance(0);
            case 1:
                return FragmentNews.newInstance(1);
            case 2:
                return FragmentNews.newInstance(2);

            default:
                return null;
        }
    }
}