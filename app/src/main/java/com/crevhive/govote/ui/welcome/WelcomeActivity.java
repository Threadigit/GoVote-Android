package com.crevhive.govote.ui.welcome;

/**
 * @author toluAdetuyi
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crevhive.govote.R;
import com.crevhive.govote.ui.search.SearchActivity;
import com.crevhive.govote.ui.welcome.slides.Slide0Fragment;
import com.crevhive.govote.ui.welcome.slides.Slide1Fragment;
import com.crevhive.govote.ui.welcome.slides.Slide2Fragment;
import com.crevhive.govote.ui.welcome.slides.Slide3Fragment;
import com.crevhive.govote.util.CountDownTimer;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @BindView(R.id.layoutDots)
    public LinearLayout dotsLayout;

    @BindView(R.id.btn_skip)
    public Button btnSkip;

    @BindView(R.id.btn_next)
    public Button btnNext;

    private TextView[] dots;
    private ArrayList<Fragment> layoutList = new ArrayList<>();

    private WelcomePageAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        init();

        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

    }

    private void init() {

        checkversionForDisplay();

        initViewPager();

        // making notification bar transparent
        changeStatusBarColor();
    }

    private void initViewPager() {

        //add all slides layouts
        layoutList.add(new Slide0Fragment());
        layoutList.add(new Slide1Fragment());
        layoutList.add(new Slide2Fragment());
        layoutList.add(new Slide3Fragment());

        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new WelcomePageAdapter(layoutList,getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    private void addBottomDots(int currentPage) {

        dots = new TextView[layoutList.size()];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }


    private void checkversionForDisplay() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().
                    setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }


    /**
     * Update the view pager on change
     */
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layoutList.size() - 1) {

                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);

            } else {

                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Get the current item within view pager
     *
     * @param count
     * @return
     */
    private int getItem(int count) {
        return viewPager.getCurrentItem() + count;
    }


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * Launch the main search screen
     */
    private void launchMainScreen() {

        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.btn_next)
    public void onNextClick(View v) {


        int current = getItem(+1);

        if (current < layoutList.size()) {

            // move to next screen
            viewPager.setCurrentItem(current);

        } else {

            launchMainScreen();

        }
    }

    @OnClick(R.id.btn_skip)
    public void onSkipClick(View v) {

        launchMainScreen();
    }
}

