package com.qk.party.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2016/8/22.
 */
public class ActivityManagers {
    public static List<Activity> activityStack;
    private static ActivityManagers instance;

    private ActivityManagers() {
    }

    /**
     * 单一实例
     */
    public static ActivityManagers getAppManager() {
        if (instance == null) {
            instance = new ActivityManagers();
        }
        return instance;
    }
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new ArrayList<>();
        }
        activityStack.add(activity);
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}
