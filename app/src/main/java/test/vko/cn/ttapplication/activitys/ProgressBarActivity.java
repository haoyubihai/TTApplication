package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.ProgressBarView;

public class ProgressBarActivity extends AppCompatActivity {
    @Bind(R.id.pb)
    ProgressBarView progressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.bind(this);

        progressBarView.setCurrentPosition(new Random().nextInt(100));
    }
}
