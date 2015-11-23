package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;

public class ShipWaveActivity extends AppCompatActivity {
//
//    @Bind(R.id.waveView)
//    ShipWaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_wave);
        ButterKnife.bind(this);
//        startMove();
    }

//    public void startMove() {
//        ObjectAnimator moveValue = ObjectAnimator.ofInt(waveView, "translationX", 0, 300);
//        moveValue.setDuration(10000);
//        moveValue.setRepeatCount(-1);
////        moveValue.setRepeatMode(ValueAnimator.RESTART);
//        moveValue.start();
//    }
}
