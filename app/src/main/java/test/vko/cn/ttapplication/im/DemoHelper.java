package test.vko.cn.ttapplication.im;

import android.content.Context;

public class DemoHelper {
    private static DemoHelper instance = null;
    private Context appContext;

    private DemoHelper() {
    }

    public synchronized static DemoHelper getInstance() {
        if (instance == null) {
            instance = new DemoHelper();
        }
        return instance;
    }

    public void init(Context context) {

    }




}