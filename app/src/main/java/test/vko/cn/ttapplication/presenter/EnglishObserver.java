package test.vko.cn.ttapplication.presenter;

import android.content.Context;
import android.widget.Toast;

import test.vko.cn.ttapplication.listeners.Observer;
import test.vko.cn.ttapplication.listeners.Subject;

/**
 * Created by JiaRH on 2015/11/2.
 */
public class EnglishObserver implements Observer {


    private Subject math;
    private Context context;

    public EnglishObserver(Subject math, Context context) {
        this.math = math;
        this.context = context;
        math.registObserver(this);
    }

    @Override
    public void update(String msg) {
        Toast.makeText(context, msg + "English", Toast.LENGTH_LONG).show();
    }
}
