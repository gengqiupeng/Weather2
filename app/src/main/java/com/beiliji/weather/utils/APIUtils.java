package com.beiliji.weather.utils;

public class APIUtils {
    private static String Host = "http://104.171.164.71/weather/index.php?";


    public static String getWeatherLink(int code) {
        return Host + "c=weather&a=weather&code="+code;
    }

    public static String getProvinceLink() {
        return Host + "c=weather&a=province";
    }

    public static String getCityLink(int id){
        return Host+"c=weather&a=city&province="+id;
    }

    public static String getDistracttLink(int id){
        return Host+"c=weather&a=distract&city="+id;
    }

    public static String getHourLink(int code){
        return Host+"c=weather&a=hour&code="+code;
    }
}