package com.crevhive.govote;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by toluadetuyi on 2/19/18.
 */

public class WelcomePageAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    int[] layouts;

    public WelcomePageAdapter(int[] layouts, LayoutInflater layoutInflater) {

        this.layouts = layouts;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
