package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.presenter.EnglishObserver;
import test.vko.cn.ttapplication.presenter.MathObserver;
import test.vko.cn.ttapplication.presenter.QQ3D;

public class ObserverActivity extends AppCompatActivity {

    @Bind(R.id.btns)
    Button btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btns)
    void onClickBtn() {
        QQ3D qq3D = new QQ3D();
        MathObserver mathObserver = new MathObserver(qq3D, this);
        EnglishObserver englishObserver = new EnglishObserver(qq3D, this);
        qq3D.setMsg("hello");
    }
}
