package com.beiliji.weather;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import org.xutils.x;

import java.util.Stack;

public class WeaApplication extends Application {
    private static WeaApplication signle;
    private static Stack<Activity> activityStack;
    private static final String TAG = "WeaApplication";

    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        signle = this;
    }

    public static WeaApplication getInstance() {
        return signle;
    }

    public void ExitApp() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            Log.i(TAG,e.toString());
        }
    }
    /**
     * 把Activity添加到栈中
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
        Log.i(TAG, "当前回退栈的Activity数量:" + activityStack.size());
    }

    /**
     * 当前Activity
     *
     * @return
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }


    public void finishCurrentActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}