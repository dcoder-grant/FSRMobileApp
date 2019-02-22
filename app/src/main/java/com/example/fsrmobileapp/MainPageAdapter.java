package com.example.fsrmobileapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mainFragmentList = new ArrayList<>();
    private final List<String> mainFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mainFragmentList.add(fragment);
        mainFragmentTitleList.add(title);
    }

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mainFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mainFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mainFragmentList.size();
    }
}
