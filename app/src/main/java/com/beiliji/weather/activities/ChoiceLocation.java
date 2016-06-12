package com.beiliji.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beiliji.weather.R;
import com.beiliji.weather.WeaApplication;
import com.beiliji.weather.adapter.ProvinceAdapter;
import com.beiliji.weather.bean.ProvinceBean;
import com.beiliji.weather.utils.APIUtils;
import com.beiliji.weather.utils.JSONParser;
import com.beiliji.weather.utils.NetCall;
import com.beiliji.weather.utils.UIHelper;

import java.util.List;

/**
 * coder by 中资北方 on 2016-6-8.
 */
public class ChoiceLocation extends AppCompatActivity{

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeaApplication.getInstance().addActivity(this);
        setContentView(R.layout.choice_location);
        listView = (ListView) findViewById(R.id.listview_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择省份");
        initData();
    }

    private String initData(){
        UIHelper.getDataFromNet(APIUtils.getProvinceLink(),new NetCall(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                List<ProvinceBean> lists = JSONParser.parserProvince(result);
                initListView(lists);
            }

            @Override
            public void onError(Throwable throwable, boolean paramBoolean) {
                super.onError(throwable, paramBoolean);
                Log.i("location",throwable.toString());
            }
        });
        return null;
    }

    private void initListView(final List<ProvinceBean> lists){
        ProvinceAdapter adapter = new ProvinceAdapter(lists,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int provinceId = lists.get(position).getId();
                Intent intent= new Intent(ChoiceLocation.this,ChoiceCity.class);
                intent.putExtra("id",provinceId);
                startActivity(intent);
            }
        });
    }

}
