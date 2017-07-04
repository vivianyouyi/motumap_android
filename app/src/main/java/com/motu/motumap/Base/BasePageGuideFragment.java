package com.motu.motumap.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motu.motumap.login.PageGuideActivity;

/**
 * Created by mm on 2016/7/25.
 */
public abstract class BasePageGuideFragment extends BaseFragment {

    private PageGuideActivity mCalibrationActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();

    }

    public PageGuideActivity getInstance() {
        mCalibrationActivity = (PageGuideActivity) getActivity();
        return mCalibrationActivity;
    }

    public abstract View initView();
}
