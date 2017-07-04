package com.motu.motumap.login;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.motu.motumap.Base.BasePageGuideFragment;
import com.motu.motumap.R;

/**
 * Created by vivian on 2017/7/4.
 */
public class PageGuideFragment1 extends BasePageGuideFragment {

    private View mView;


    @Override
    public View initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_pageguide1, null);
        return mView;
    }

}
