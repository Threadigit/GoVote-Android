package com.crevhive.govote.ui.welcome;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author toluadetuyi
 *         Welcome Page Adapter
 */

public class WelcomePageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> layoutList;

    public WelcomePageAdapter(ArrayList<Fragment> layoutList, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.layoutList = layoutList;
    }

    @Override
    public int getCount() {
        return layoutList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return layoutList.get(position);
    }
}
