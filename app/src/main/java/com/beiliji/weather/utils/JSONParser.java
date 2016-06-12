package com.beiliji.weather.utils;

import com.beiliji.weather.bean.CityBean;
import com.beiliji.weather.bean.ProvinceBean;
import com.beiliji.weather.bean.WeatherBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JSONParser {

    public static List<WeatherBean> parserWeather(String paramString) {
        return new Gson().fromJson(paramString, new TypeToken<List<WeatherBean>>() {
        }.getType());
    }

    public static List<ProvinceBean> parserProvince(String json){
        return new Gson().fromJson(json,new TypeToken<List<ProvinceBean>>(){}.getType());
    }

    public static List<CityBean> praserCity(String json){
        return new Gson().fromJson(json,new TypeToken<List<CityBean>>(){}.getType());
    }

    public static JsonElement parserHour(String json){
        return new Gson().fromJson(json, JsonElement.class);
    }
}