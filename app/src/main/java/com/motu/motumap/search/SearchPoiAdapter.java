package com.motu.motumap.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.motu.motumap.R;

import java.util.List;

public class SearchPoiAdapter extends BaseAdapter {
    private List<SearchPoiEntity> mList;
    private Context context;

    public SearchPoiAdapter(List<SearchPoiEntity> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setList(List<SearchPoiEntity> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {

        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.mo_search_poi_item, null);

            holder.destination_rly =  view.findViewById(R.id.destination_rly);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_district = (TextView) view.findViewById(R.id.tv_district);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_name.setText(mList.get(position).getAddrname());
        holder.tv_district.setText(mList.get(position).getDistrict());

        return view;
    }

    static class ViewHolder {
        public View destination_rly;
        public TextView tv_name;
        public TextView tv_district;
    }

}
