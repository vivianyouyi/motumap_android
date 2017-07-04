package com.motu.motumap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.motu.motumap.utils.LogUtil;

import java.util.List;

/**
 * Created by liweiwei on 2016/7/25.
 */
public class ViewPagerAdpter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String tag = "AIOPageradpter";
    public ViewPagerAdpter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.list = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtil.d(tag,"pos"+position);
        return list.get(position);

    }

    @Override
    public int getCount() {
        return list.size();
    }
}
