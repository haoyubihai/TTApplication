package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.BenzierView;

public class BenzierActivity extends AppCompatActivity {

    @Bind(R.id.benView)
    BenzierView benView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benzier);
        ButterKnife.bind(this);

    }
}
