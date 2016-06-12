package com.beiliji.weather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beiliji.weather.R;
import com.beiliji.weather.adapter.HourAdapter;
import com.beiliji.weather.utils.APIUtils;
import com.beiliji.weather.utils.FileUtils;
import com.beiliji.weather.utils.JSONParser;
import com.beiliji.weather.utils.NetCall;
import com.beiliji.weather.utils.UIHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class HourFragment extends BaseFragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_hour, null);
        //MainActivity activity = (MainActivity) getActivity();
        //activity.setTitle("逐小时预报");
        listView = (ListView) view.findViewById(R.id.listview_hour);
        initData();
        return view;
    }

    public void initData() {
        int code = FileUtils.readCode();
        UIHelper.getDataFromNet(APIUtils.getHourLink(code), new NetCall() {
            @Override
            public void onSuccess(String result) {
                Log.i("hour", result);
                JsonElement element = JSONParser.parserHour(result);
                JsonArray array = element.getAsJsonArray();
                initListView(array);
            }
        });
    }

    public void initListView(JsonArray array) {
        HourAdapter adapter = new HourAdapter(array, getActivity());
        listView.setAdapter(adapter);
    }

}