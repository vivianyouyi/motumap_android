package com.motu.motumap.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.help.Tip;
import com.motu.motumap.R;
import com.motu.motumap.me.PersonalCenterActivity;
import com.motu.motumap.route.AvoidRouteActivity;
import com.motu.motumap.view.ShadowProperty;
import com.motu.motumap.view.ShadowViewDrawable;


public class SearchResultActivity extends Activity implements View.OnClickListener {

    private Context mContext;

    private View title_bg_lyt;
    private ImageView back_iv;
    private ImageView forbidden_iv;
    private TextView destination_tv;
    private TextView tv_name;
    private TextView tv_district;
    private TextView tv_address;
    private Button button_nomal_navi;


    private AMap aMap;
    private MapView mapView;
    private LatLng latlng;
    private Tip tip;
    Marker growMarker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mo_activity_search_result);
        mContext = SearchResultActivity.this;
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置; 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
		 * 则需要在离线地图下载和使用地图页面都进行路径设置
		 */
        // Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
        // MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        initData();
        initView();
        initMap();
        startGrowAnimation();
    }

    private void initData() {
        Intent intent = getIntent();
        tip =(Tip) intent.getParcelableExtra("destination");
        System.out.println("liweiwei....tip = " + tip.getPoint().toString());
        latlng = new LatLng(tip.getPoint().getLatitude(), tip.getPoint().getLongitude());
    }

    /**
     * 初始化
     */
    private void initView() {
        title_bg_lyt = findViewById(R.id.title_bg_lyt);
        back_iv = (ImageView) findViewById(R.id.back_iv);
        forbidden_iv = (ImageView) findViewById(R.id.forbidden_iv);
        destination_tv = (TextView) findViewById(R.id.destination_tv);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_district = (TextView) findViewById(R.id.tv_district);
        tv_address = (TextView) findViewById(R.id.tv_address);
        button_nomal_navi = (Button) findViewById(R.id.button_nomal_navi);

        destination_tv.setText(tip.getName());
        tv_name.setText(tip.getName());
        tv_district.setText(tip.getDistrict());
        tv_address.setText(tip.getAddress());

        ShadowProperty sp = new ShadowProperty()
                .setShadowColor(0x77000000)
                .setShadowDy(dip2px(this, 0.5f))
                .setShadowRadius(dip2px(this, 3))
                .setShadowSide(ShadowProperty.ALL);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.WHITE, 0, 0);
        ViewCompat.setBackground(title_bg_lyt, sd);
        ViewCompat.setLayerType(title_bg_lyt, ViewCompat.LAYER_TYPE_SOFTWARE, null);

        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        forbidden_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button_nomal_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, AvoidRouteActivity.class);
                intent.putExtra("CurrentCity","上海市");
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {

        if (aMap == null) {
            aMap = mapView.getMap();
        }

        aMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latlng, 18, 30, 0)));

        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                addGrowMarker();
            }
        });
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
    /**
     * 添加一个从地上生长的Marker
     */
    public void addGrowMarker() {
        if (growMarker == null) {

            MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.location))
                    .position(latlng);
            growMarker = aMap.addMarker(markerOptions);
        }

        startGrowAnimation();
    }

    /**
     * 地上生长的Marker
     */
    private void startGrowAnimation() {
        if (growMarker != null) {
            Animation animation = new ScaleAnimation(0, 1, 0, 1);
            animation.setInterpolator(new LinearInterpolator());
            //整个移动所需要的时间
            animation.setDuration(1000);
            //设置动画
            growMarker.setAnimation(animation);
            //开始动画
            growMarker.startAnimation();
        }
    }
    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
