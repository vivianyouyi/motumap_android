package com.motu.motumap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.motu.motumap.me.PersonalCenterActivity;
import com.motu.motumap.search.SearchActivity;
import com.motu.motumap.view.ShadowProperty;
import com.motu.motumap.view.ShadowViewDrawable;

import static com.amap.map3d.demo.indoor.IndoorFloorSwitchView.dip2px;

/**
 * Created by Vivian on 2017/5/6.
 */
public class MainPageActivity extends Activity implements AMap.OnMyLocationChangeListener {
    private Context mContext;
    private AMap aMap;
    private MapView mapView;
    private MyLocationStyle myLocationStyle;

    private View search_bg_lyt;
    private ImageView me_imageview;
    private ImageView forbidden_iv;
    private TextView input_edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示程序的标题栏
        setContentView(R.layout.mo_activity_mainpage);
        mContext = MainPageActivity.this;
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);//
        initView();
        initMap();
    }

    /**
     * 初始化
     */
    private void initView() {
        search_bg_lyt =  findViewById(R.id.search_bg_lyt);
        me_imageview = (ImageView) findViewById(R.id.me_imageview);
        forbidden_iv = (ImageView) findViewById(R.id.forbidden_iv);
        input_edittext = (TextView) findViewById(R.id.input_edittext);


        ShadowProperty sp = new ShadowProperty()
                .setShadowColor(0x77000000)
                .setShadowDy(dip2px(this, 0.5f))
                .setShadowRadius(dip2px(this, 3))
                .setShadowSide(ShadowProperty.ALL);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.WHITE, 0, 0);
        ViewCompat.setBackground(search_bg_lyt, sd);
        ViewCompat.setLayerType(search_bg_lyt, ViewCompat.LAYER_TYPE_SOFTWARE, null);

        me_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, PersonalCenterActivity.class);
                intent.putExtra("CurrentCity","上海市");
                startActivity(intent);
            }
        });


        input_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mContext, SearchActivity.class));
            }
        });

        forbidden_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 初始化
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {

        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = new MyLocationStyle();
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE));

        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if (location != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }
}
