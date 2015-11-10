package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.StarView;

public class StarActivity extends AppCompatActivity {

    @Bind(R.id.star_view)
    StarView starView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        ButterKnife.bind(this);
        starView.setText("90");
    }
}
