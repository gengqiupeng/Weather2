package com.beiliji.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beiliji.weather.MainActivity;
import com.beiliji.weather.R;
import com.beiliji.weather.WeaApplication;
import com.beiliji.weather.adapter.CityAdapter;
import com.beiliji.weather.bean.CityBean;
import com.beiliji.weather.utils.APIUtils;
import com.beiliji.weather.utils.FileUtils;
import com.beiliji.weather.utils.JSONParser;
import com.beiliji.weather.utils.NetCall;
import com.beiliji.weather.utils.UIHelper;

import java.util.List;

/**
 * coder by 中资北方 on 2016-6-8.
 */
public class ChoiceDistract extends AppCompatActivity{
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeaApplication.getInstance().addActivity(this);
        setContentView(R.layout.choice_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择位置");
        listView = (ListView) findViewById(R.id.listview_location);
        initData();
    }
    private String initData(){
        Log.i("city","go");
        Intent intent = getIntent();
        int cityId = intent.getIntExtra("id",01);
        UIHelper.getDataFromNet(APIUtils.getDistracttLink(cityId),new NetCall(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("city",result);
                List<CityBean> lists = JSONParser.praserCity(result);
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

    private void initListView(final List<CityBean> lists){
        CityAdapter adapter = new CityAdapter(lists,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int code = lists.get(position).getCode();
                String name = lists.get(position).getName();
                FileUtils.storeCodeName(code,name);
                WeaApplication.getInstance().ExitApp();
                Intent intent = new Intent(ChoiceDistract.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
