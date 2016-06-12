package com.beiliji.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.beiliji.weather.WeaApplication;

/**
 * coder by 中资北方 on 2016-6-8.
 */
public class FileUtils {

    public static void storeCodeName(int code, String name) {
        SharedPreferences sharedPreferences = WeaApplication.getInstance().getSharedPreferences("weather", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putInt("code", code);
        editor.apply();
    }

    public static String readName() {
        SharedPreferences sharedPreferences = WeaApplication.getInstance().getSharedPreferences("weather", Context.MODE_MULTI_PROCESS);
        return sharedPreferences.getString("name", "郑州");
    }

    public static int readCode() {
        SharedPreferences sharedPreferences = WeaApplication.getInstance().getSharedPreferences("weather", Context.MODE_MULTI_PROCESS);
        return sharedPreferences.getInt("code", 101180101);
    }

}
