package com.beiliji.weather.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class UIHelper {
    public static void getDataFromNet(String paramString, Callback.CommonCallback<String> paramCommonCallback) {
        x.http().get(new RequestParams(paramString), paramCommonCallback);
    }
}