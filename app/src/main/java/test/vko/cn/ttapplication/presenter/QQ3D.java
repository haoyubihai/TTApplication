package test.vko.cn.ttapplication.presenter;

import java.util.ArrayList;
import java.util.List;

import test.vko.cn.ttapplication.listeners.Observer;
import test.vko.cn.ttapplication.listeners.Subject;

/**
 * Created by JiaRH on 2015/11/2.
 */
public class QQ3D implements Subject {
    List<Observer> observers = new ArrayList<>();
    private String msg;

    @Override
    public void registObserver(Observer O) {
        observers.add(O);
    }

    @Override
    public void unRegistObserver(Observer O) {

        int i = observers.indexOf(O);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyData() {

        for (Observer oo : observers) {

            oo.update(msg);
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyData();
    }
}
