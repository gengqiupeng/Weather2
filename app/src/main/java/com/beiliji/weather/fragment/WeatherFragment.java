package com.beiliji.weather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beiliji.weather.R;
import com.beiliji.weather.adapter.WeatherAdapter;
import com.beiliji.weather.bean.WeatherBean;
import com.beiliji.weather.utils.APIUtils;
import com.beiliji.weather.utils.FileUtils;
import com.beiliji.weather.utils.JSONParser;
import com.beiliji.weather.utils.NetCall;
import com.beiliji.weather.utils.UIHelper;

import java.util.List;

public class WeatherFragment extends BaseFragment {
    private ListView listView;


    private void initListView(List<WeatherBean> paramList) {
        WeatherAdapter localWeatherAdapter = new WeatherAdapter(paramList, getActivity());
        this.listView.setAdapter(localWeatherAdapter);
    }

    public void getData() {
        int code = FileUtils.readCode();
        UIHelper.getDataFromNet(APIUtils.getWeatherLink(code), new NetCall() {
            @Override
            public void onSuccess(String result) {
                List<WeatherBean> list = JSONParser.parserWeather(result);
                initListView(list);
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.frament_weather, null);
        this.listView = ((ListView) localView.findViewById(R.id.listview_weather));
        //MainActivity activity = (MainActivity) getActivity();
        //activity.setTitle("天气预报");
        getData();
        return localView;
    }
}