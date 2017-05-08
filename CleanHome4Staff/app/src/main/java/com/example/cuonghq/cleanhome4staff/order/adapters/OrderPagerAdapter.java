package com.example.cuonghq.cleanhome4staff.order.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cuonghq.cleanhome4staff.order.fragments.ViewOrderFragment;


/**
 * Created by Cuonghq on 4/25/2017.
 */

public class OrderPagerAdapter extends FragmentStatePagerAdapter{

    int numOfTabs;
    private FragmentManager fragmentManager;

    public OrderPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        ViewOrderFragment fragment = new ViewOrderFragment();
        fragment.setAdapter(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
