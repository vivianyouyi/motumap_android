package com.motu.motumap.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.motu.motumap.R;

/**
 * Created by 20170418003 on 2017/5/12.
 */

public class MyPlateActivity  extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mo_activity_myplate);
        initToolBar();
    }


    private void initToolBar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.button_back);

        mTitle = (TextView) mToolbar.findViewById(R.id.textview_toolbar_title);
        mTitle.setText("车牌设置");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}