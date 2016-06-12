package com.beiliji.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beiliji.weather.R;
import com.beiliji.weather.bean.ProvinceBean;

import java.util.List;

/**
 * coder by 中资北方 on 2016-6-8.
 */
public class ProvinceAdapter extends BaseAdapter{

    private List<ProvinceBean> list;
    private Context context;

    public ProvinceAdapter(List<ProvinceBean> list,Context context) {
        this.list= list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_location_item,parent,false);
            holder.text = (TextView) convertView.findViewById(R.id.text_location);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProvinceBean bean = list.get(position);
        holder.text.setText(bean.getName());
        return convertView;
    }

    class ViewHolder{
        private TextView text;
    }
}
