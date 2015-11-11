package test.vko.cn.ttapplication;

import android.app.Application;
import android.content.Context;

import test.vko.cn.ttapplication.presenter.HuanXinHelper;

/**
 * Created by JiaRH on 2015/11/10.
 */
public class TTapplication extends Application {
    private Context mContext;
    private static TTapplication INSTANCE=null;

    public static TTapplication getInstance(){

        return INSTANCE;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        initHuanXin();
    }

    private void initData() {
        mContext = this;
        INSTANCE = this;
    }

    private void initHuanXin() {
        HuanXinHelper.getInstance().init(mContext);
    }
}
