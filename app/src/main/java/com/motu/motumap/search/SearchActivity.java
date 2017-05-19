package com.motu.motumap.search;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.map3d.demo.util.AMapUtil;
import com.amap.map3d.demo.util.ToastUtil;
import com.motu.motumap.R;
import com.motu.motumap.common.Constant;
import com.motu.motumap.me.PersonalCenterActivity;
import com.motu.motumap.utils.DeviceUtils;
import com.motu.motumap.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * poisearch搜索
 */
public class SearchActivity extends FragmentActivity implements TextWatcher, View.OnClickListener, Inputtips.InputtipsListener {

    private Context mContext;
    private ImageView back_imageview;
    private ListView listView;
    private EditText searchText;// 输入搜索关键字
    private TextView nodata_tv;
    private View clean_his_lyt;
    private SearchPoiAdapter mAdapter;
    private List<SearchPoiEntity> mList;
    private List<Tip> mTipList;

    private ProgressDialog progDialog = null;// 搜索时进度条
    private String currentCity;// 要输入的城市名字或者城市区号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // setContentView(R.layout.poikeywordsearch_activity);
        setContentView(R.layout.mo_activity_search);
        mContext = SearchActivity.this;
        initView();
        //getHistoryData();
    }


    /**
     * 设置页面监听
     */
    private void initView() {
        currentCity = "上海市";
        mList = new ArrayList<SearchPoiEntity>();
        mAdapter = new SearchPoiAdapter(mList, SearchActivity.this);
        searchText = (EditText) findViewById(R.id.keyword_edittext);
        nodata_tv = (TextView) findViewById(R.id.nodata_tv);
        clean_his_lyt = findViewById(R.id.clean_his_lyt);
        listView = (ListView) findViewById(R.id.history_lv);
        listView.setAdapter(mAdapter);
        back_imageview = (ImageView) findViewById(R.id.back_imageview);
        back_imageview.setOnClickListener(this);
        clean_his_lyt.setOnClickListener(this);
        searchText.addTextChangedListener(this);// 添加文本输入框监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceUtils.dropKeyBoard(mContext, searchText);
                if (mList != null && mList.size() > position) {
                    String enAddr = mList.get(position).getName() + "," + mList.get(position).getDistrict() + ","
                            + mList.get(position).getLatitude() + "," + mList.get(position).getLongitude();
                   // saveHistorySearch(enAddr);

                    Intent intent = new Intent(mContext, SearchResultActivity.class);
                    intent.putExtra("destination", mTipList.get(position));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        System.out.println("liweiwei... newText = " + newText);
        clean_his_lyt.setVisibility(View.GONE);
        if (!AMapUtil.IsEmptyOrNullString(newText) && newText.length() > 1) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, currentCity);
            Inputtips inputTips = new Inputtips(SearchActivity.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }
    }

    /**
     * Button点击事件回调方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 点击返回按钮
             */
            case R.id.back_imageview:

                finish();
                break;
            case R.id.clean_his_lyt:
                cleanHistoryData();
                break;
            default:
                break;
        }
    }


    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {

        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回

            nodata_tv.setVisibility(View.GONE);
            mList.clear();
            mTipList = new ArrayList<>();
            mTipList.clear();
            for (Tip tip : tipList) {
                if (tip.getPoint() != null && tip.getPoint().getLatitude() > 0.0 && tip.getPoint().getLongitude() > 0.0) {
                    System.out.println("liweiwei... tip " + tip.toString());
                    System.out.println("liweiwei... tip point" + tip.getPoint().toString());
                    SearchPoiEntity entity = new SearchPoiEntity();
                    entity.setName(tip.getName());
                    entity.setDistrict(tip.getDistrict());
                    entity.setLatitude(tip.getPoint().getLatitude() + "");
                    entity.setLongitude(tip.getPoint().getLongitude() + "");
                    mList.add(entity);
                    mTipList.add(tip);
                }
            }
            mAdapter.setList(mList);
        } else {
            nodata_tv.setVisibility(View.VISIBLE);
            ToastUtil.showerror(this, rCode);
        }

    }

    /**
     * 获取缓存的搜索历史数据
     */
    private void getHistoryData() {
        String result = SpUtils.getInstance(mContext).getString(Constant.SpConstant.SEARCH_HISTORY, null);
        if (result == null) {
            nodata_tv.setVisibility(View.VISIBLE);
            return;
        }
        String[] array = result.split(";");

        System.out.println("liweiwei... result = " + result);
        SearchPoiEntity poi;
        mList.clear();
        for (int i = array.length - 1; i >= 0; i--) {

            String[] items = array[i].split(",");
            poi = new SearchPoiEntity();
            poi.setName(items[0]);
            poi.setDistrict(items[1]);
            poi.setLatitude(items[2]);
            poi.setLongitude(items[3]);
            mList.add(poi);
        }

        if (mList.size() > 10) {
            String strs = "";
            String str = "";
            for (int i = 9; i >= 0; i--) {
                str = mList.get(i).getName() + "," + mList.get(i).getDistrict()  + "," + mList.get(i).getLatitude() + "," + mList.get(i).getLongitude();
                if (i == 0) {
                    strs = strs + str;
                } else {
                    strs = strs + str + ";";
                }
            }
            SpUtils.getInstance(mContext).putString(Constant.SpConstant.SEARCH_HISTORY, strs);
        }

        mAdapter.setList(mList);

        nodata_tv.setVisibility(View.GONE);
        clean_his_lyt.setVisibility(View.VISIBLE);
    }


    /**
     * 保存搜索的数据
     */
    private void saveHistorySearch(String enAddr) {
        String res = SpUtils.getInstance(mContext).getString(Constant.SpConstant.SEARCH_HISTORY, null);
        if (res == null) {
            res = enAddr;
        } else {
            if (!res.contains(enAddr)) {
                res = res + ";" + enAddr;
            }
        }
        SpUtils.getInstance(mContext).putString(Constant.SpConstant.SEARCH_HISTORY, res);
    }


    private void cleanHistoryData() {
        SpUtils.getInstance(mContext).putString(Constant.SpConstant.SEARCH_HISTORY, null);
    }
}
