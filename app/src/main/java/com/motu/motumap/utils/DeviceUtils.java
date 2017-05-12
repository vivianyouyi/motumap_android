package com.motu.motumap.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Created by liweiwei1 on 2015/12/17.
 */
public class DeviceUtils {

    /**
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * @return
     */
    public static int getScreenHeigt(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.heightPixels;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getMusicCachePath() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Ecolink/Music/";
    }

    public static String getApkCachePath() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Ecolink/Apk/";
    }

    /**
     * 弹出键盘
     *
     * @param mContext
     */
    public static void popKeyBoard(Context mContext) {
        InputMethodManager imm =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 键盘关闭
     *
     * @param mContext
     */
    public static void dropKeyBoard(Context mContext, View view) {
        //键盘弹下
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }


    /**
     * 判断当前应用程序是否处于前台
     */
    public static boolean isApplicationBroughtToFront(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return false;
            }
        }
        return true;
    }

    public static String getDeviceName() {
        String devicesNmae = android.os.Build.MANUFACTURER;
        Log.i("", "getDeviceName=" + devicesNmae);
        return devicesNmae;
    }

    public static String getDeviceModel() {
        String devicesModel = android.os.Build.MODEL;
        Log.i("", "devicesModel=" + devicesModel);
        return devicesModel;
    }

}
