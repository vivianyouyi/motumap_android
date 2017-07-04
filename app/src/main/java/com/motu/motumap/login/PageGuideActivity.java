package com.motu.motumap.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.motu.motumap.Adapter.ViewPagerAdpter;
import com.motu.motumap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 2017/7/4.
 */

public class PageGuideActivity extends FragmentActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPager mViewPager;
    private LinearLayout aio_dots;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageguide);
        mViewPager = (ViewPager) findViewById(R.id.aio_viewpager);
        aio_dots = (LinearLayout) findViewById(R.id.ll_dots);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        aio_dots.getChildAt(0).setSelected(true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                aio_dots.getChildAt(position).setSelected(true);
                for (int i = 0; i < mFragmentList.size(); i++) {
                    if (i != position) {
                        aio_dots.getChildAt(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {
        mViewPager.setAdapter(new ViewPagerAdpter(getSupportFragmentManager(), mFragmentList));
        initDots();
    }

    private void initData() {
        mFragmentList.add(new PageGuideFragment1());
        mFragmentList.add(new PageGuideFragment2());
        mFragmentList.add(new PageGuideFragment3());
        mFragmentList.add(new PageGuideFragment4());
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public int getTotalIndex() {
        return mFragmentList.size();
    }

    private void initDots() {
        int width = (int) (6 * getResources().getDisplayMetrics().density + 0.5f);
        for (int i = 0; i < mFragmentList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.selector_operation_ll);
            android.widget.LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }
            aio_dots.addView(imageView, params);
            aio_dots.getChildAt(i).setSelected(false);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
