package test.vko.cn.ttapplication.listeners;

/**
 * Created by JiaRH on 2015/11/2.
 */
public interface Subject {
    void registObserver(Observer O);

    void unRegistObserver(Observer O);

    void notifyData();
}
