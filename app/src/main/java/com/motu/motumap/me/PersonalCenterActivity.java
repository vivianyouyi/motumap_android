package com.motu.motumap.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.motu.motumap.R;

/**
 * Created by 20170418003 on 2017/5/9.
 */

public class PersonalCenterActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar mToolbar;
    private TextView mTitle;

    private View my_plate_rly;
    private View all_restriction_rly;
    private View no_highway_rly;
    private View no_elevated_rly;
    private View policy_rly;
    private View about_us_rly;
    private View user_agency_rly;
    private View score_rly;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mo_activity_personalcenter);
        mContext = PersonalCenterActivity.this;
        initToolBar();
        initView();
    }

    private void initToolBar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.button_back);

        mTitle = (TextView) mToolbar.findViewById(R.id.textview_toolbar_title);
        mTitle.setText("我");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {

        my_plate_rly = findViewById(R.id.my_plate_rly);
        all_restriction_rly = findViewById(R.id.all_restriction_rly);
        no_highway_rly = findViewById(R.id.no_highway_rly);
        no_elevated_rly = findViewById(R.id.no_elevated_rly);
        policy_rly = findViewById(R.id.policy_rly);
        about_us_rly = findViewById(R.id.about_us_rly);
        user_agency_rly = findViewById(R.id.user_agency_rly);
        score_rly = findViewById(R.id.score_rly);

        my_plate_rly.setOnClickListener(this);
        all_restriction_rly.setOnClickListener(this);
        no_highway_rly.setOnClickListener(this);
        no_elevated_rly.setOnClickListener(this);
        policy_rly.setOnClickListener(this);
        about_us_rly.setOnClickListener(this);
        user_agency_rly.setOnClickListener(this);
        score_rly.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.my_plate_rly:

                intent = new Intent(mContext, MyPlateActivity.class);
                startActivity(intent);

                break;
            case R.id.all_restriction_rly:

                intent = new Intent(mContext, AllRestrictionActivity.class);
                startActivity(intent);

                break;
            case R.id.no_highway_rly:
                break;
            case R.id.no_elevated_rly:

                break;
            case R.id.policy_rly:
                intent = new Intent(mContext, RestrictionPolicyActivity.class);
                startActivity(intent);

                break;
            case R.id.about_us_rly:
                intent = new Intent(mContext, AboutUsActivity.class);
                startActivity(intent);

                break;
            case R.id.user_agency_rly:
                intent = new Intent(mContext, UserAgentActivity.class);
                startActivity(intent);

                break;
            case R.id.score_rly:
                intent = new Intent(mContext, ScoreActivity.class);
                startActivity(intent);

                break;

        }
    }
}
