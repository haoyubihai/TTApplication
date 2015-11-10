package test.vko.cn.ttapplication.testclass;

/**
 * Created by JiaRH on 2015/10/14.
 */
public class SingleTon {
    private static SingleTon ourInstance = new SingleTon();

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        return ourInstance;
    }
}
