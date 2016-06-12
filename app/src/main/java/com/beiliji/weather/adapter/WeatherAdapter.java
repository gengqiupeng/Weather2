package com.beiliji.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beiliji.weather.R;
import com.beiliji.weather.bean.WeatherBean;

import org.xutils.x;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    private Context context;
    private List<WeatherBean> lists;

    public WeatherAdapter(List<WeatherBean> paramList, Context paramContext) {
        this.context = paramContext;
        this.lists = paramList;
    }


    public int getCount() {
        return this.lists.size();
    }

    public Object getItem(int paramInt) {
        return this.lists.get(paramInt);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        WeatherBean bean = lists.get(position);
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_weatehr_item,parent,false);
            holder = new ViewHolder();
            holder.date= (TextView) convertView.findViewById(R.id.data);
            holder.tem= (TextView) convertView.findViewById(R.id.tem);
            holder.wea_day= (ImageView) convertView.findViewById(R.id.wea_day);
            holder.wea_nig= (ImageView) convertView.findViewById(R.id.wea_nig);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(bean.getDate());
        holder.tem.setText(bean.getTem_hig() + bean.getTem_down());
        x.image().bind(holder.wea_day, "http://i.tq121.com.cn/i/mobile/images/"+bean.getWeather_day());
        x.image().bind(holder.wea_nig, "http://i.tq121.com.cn/i/mobile/images/"+bean.getWeather_nig());
        return convertView;
    }

    public class ViewHolder {
        public TextView date;
        public TextView tem;
        public ImageView wea_day;
        public ImageView wea_nig;
    }
}