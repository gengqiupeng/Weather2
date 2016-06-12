package com.beiliji.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beiliji.weather.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.xutils.x;

public class HourAdapter extends BaseAdapter {
    private Context context;
    private JsonArray array;

    public HourAdapter(JsonArray array, Context paramContext) {
        this.context = paramContext;
        this.array = array;
    }


    public int getCount() {
        return array.size();
    }

    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        JsonElement element = array.get(position);
        JsonArray array = element.getAsJsonArray();
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_hour_item,parent,false);
            holder = new ViewHolder();
            holder.date= (TextView) convertView.findViewById(R.id.text_date);
            holder.txt_wea= (TextView) convertView.findViewById(R.id.text_wea);
            holder.img_wea= (ImageView) convertView.findViewById(R.id.image_wea);
            holder.wind_dir= (TextView) convertView.findViewById(R.id.text_wind_dir);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(array.get(0)+"");
        String image = array.get(1)+"";
        String[] result = image.split("\"");
        x.image().bind(holder.img_wea, "http://i.tq121.com.cn/i/mobile/images/"+result[1]+".png");
        holder.txt_wea.setText(array.get(2)+"");
        holder.wind_dir.setText(array.get(3)+"");
        return convertView;
    }

    public class ViewHolder {
        public TextView date;
        public ImageView img_wea;
        public TextView txt_wea;
        public TextView wind_dir;
    }
}