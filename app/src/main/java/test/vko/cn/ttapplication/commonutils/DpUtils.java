package test.vko.cn.ttapplication.commonutils;

import android.content.Context;

/**
 * Created by JiaRH on 2015/10/23.
 */
public class DpUtils {
    public static int dp2px(Context context, int dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }

    public static int ScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int ScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    public static float ScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
