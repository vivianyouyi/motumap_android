package com.motu.motumap;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.motu.motumap.Base.BaseActivity;
import com.motu.motumap.common.GlobalConstants;
import com.motu.motumap.login.PageGuideActivity;
import com.motu.motumap.utils.SpUtils;

/**
 * Created by vivian on 2017/7/4.
 */

public class StartActivity extends BaseActivity {

    private MyCountDownTimer myCountDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        myCountDownTimer = new MyCountDownTimer(2000, 1000);
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if (!isFirstTime()) {
                SpUtils.getInstance(mContext).putBoolean(GlobalConstants.SP_IS_FIRSTTIME, true);
                startActivity(new Intent(StartActivity.this, PageGuideActivity.class));
            } else {
                startActivity(new Intent(StartActivity.this, MainPageActivity.class));
            }
            finish();
        }
    }

    @Override
    public void onResume() {
        if (myCountDownTimer != null) {
            myCountDownTimer.start();
        }

        super.onResume();
    }

    @Override
    public void onPause() {
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
