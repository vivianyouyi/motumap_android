package com.motu.motumap.Base;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;


/**
 * @author
 */
public class BaseFragment extends Fragment  {
    public AsyncTask currentTask;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        StatService.onEventEnd(getActivity(), BaiDuConstants.EVENT_SINGLE_TIME,"单次使用时长");
//        StatService.onEventEnd(getActivity(), BaiDuConstants.EVENT_DYA_TIME,"每日使用时长");
    }
}
