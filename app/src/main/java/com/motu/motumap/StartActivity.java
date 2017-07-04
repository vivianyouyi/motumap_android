package com.motu.motumap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by vivian on 2017/7/4.
 */

public class StartActivity extends Activity {

    private MyCountDownTimer myCountDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (isLogin()) {
            setStartBackground();
            backgroundsetOnClick();
            myCountDownTimer = new MyCountDownTimer(6000, 50);
        } else {
            welcomeView.setVisibility(View.VISIBLE);
        }
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
