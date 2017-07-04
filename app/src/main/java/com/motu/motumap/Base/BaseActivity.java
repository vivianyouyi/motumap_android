package com.motu.motumap.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.motu.motumap.common.GlobalConstants;
import com.motu.motumap.utils.SpUtils;

/**
 * @author Ly
 */
public abstract class BaseActivity extends Activity {


    protected static Context mContext;
    private static SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;

    }

    public static boolean isFirstTime() {
        return SpUtils.getInstance(mContext).getBoolean(GlobalConstants.SP_IS_FIRSTTIME, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
