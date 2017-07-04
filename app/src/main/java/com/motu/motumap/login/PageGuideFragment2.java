package com.motu.motumap.login;

import android.view.View;

import com.motu.motumap.Base.BasePageGuideFragment;
import com.motu.motumap.R;

/**
 * Created by vivian on 2017/7/4.
 */
public class PageGuideFragment2 extends BasePageGuideFragment {


    private View mView;

    @Override
    public View initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_pageguide2, null);
        initData();
        return mView;
    }

    public void initData() {

    }
}
