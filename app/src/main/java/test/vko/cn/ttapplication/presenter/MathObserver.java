package test.vko.cn.ttapplication.presenter;

import android.content.Context;
import android.widget.Toast;

import test.vko.cn.ttapplication.listeners.Observer;
import test.vko.cn.ttapplication.listeners.Subject;

/**
 * Created by JiaRH on 2015/11/2.
 */
public class MathObserver implements Observer {


    private Subject math;
    private Context context;

    public MathObserver(Subject math, Context context) {
        this.math = math;
        this.context = context;
        math.registObserver(this);
    }

    @Override
    public void update(String msg) {
        Toast.makeText(context, msg + "Math", Toast.LENGTH_SHORT).show();
    }
}
