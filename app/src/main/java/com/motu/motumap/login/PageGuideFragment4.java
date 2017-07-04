package com.motu.motumap.login;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.motu.motumap.Base.BasePageGuideFragment;
import com.motu.motumap.MainPageActivity;
import com.motu.motumap.R;
import com.motu.motumap.StartActivity;

/**
 * Created by vivian on 2017/7/4.
 */
public class PageGuideFragment4 extends BasePageGuideFragment {

    private View mView;

    @Override
    public View initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_pageguide4, null);
        initData();
        return mView;
    }


    public void initData() {
        Button start_btn = (Button) mView.findViewById(R.id.start_btn);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                startActivity(new Intent(getInstance(), MainPageActivity.class));
            }
        });
        ViewPager viewPager = getInstance().getmViewPager();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
