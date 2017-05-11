package com.motu.motumap.search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.map3d.demo.util.AMapUtil;
import com.amap.map3d.demo.util.ToastUtil;
import com.motu.motumap.R;

import java.util.ArrayList;
import java.util.List;


/**
 * poisearch搜索
 */
public class SearchActivity extends FragmentActivity implements TextWatcher, View.OnClickListener, Inputtips.InputtipsListener {
    private ImageView back_imageview;
    private ListView listView;
    private EditText searchText;// 输入搜索关键字
    private String keyWord = "";// 要输入的poi搜索关键字
    private SearchPoiAdapter mAdapter;
    private List<Tip> mList;

    private ProgressDialog progDialog = null;// 搜索时进度条
    private String currentCity;// 要输入的城市名字或者城市区号
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // setContentView(R.layout.poikeywordsearch_activity);
        setContentView(R.layout.mo_activity_search);
        initView();
    }


    /**
     * 设置页面监听
     */
    private void initView() {
        currentCity = "上海市";
        mList = new ArrayList<Tip>();
        mAdapter = new SearchPoiAdapter(mList, SearchActivity.this);
        searchText = (EditText) findViewById(R.id.keyword_edittext);
        listView = (ListView) findViewById(R.id.history_lv);
        listView.setAdapter(mAdapter);
        back_imageview = (ImageView) findViewById(R.id.back_imageview);
        back_imageview.setOnClickListener(this);
        searchText.addTextChangedListener(this);// 添加文本输入框监听事件
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyWord);
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(SearchActivity.this, infomation);

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

                break;
            default:
                break;
        }
    }


    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {

        System.out.println("liweiwei... onGetInputtips");
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            for (Tip tip : tipList) {

                System.out.println("liweiwei... tip " +  tip.toString());
                System.out.println("liweiwei... tip point" +  tip.getPoint().toString());
            }
            mAdapter.setList(tipList);
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showerror(this, rCode);
        }

    }
}
