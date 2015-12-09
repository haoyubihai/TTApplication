package test.vko.cn.ttapplication.activitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.FlowerLayout;

public class FlowAndroidRefreshActivity extends AppCompatActivity {

    @Bind(R.id.container)
    RelativeLayout container;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_android_refresh);
        ButterKnife.bind(this);
        context=this;
        addView();
    }

    private void addView() {
        FlowerLayout flowerLayout = new FlowerLayout(context);
        for (int i=0;i<10;i++){
            TextView tv = new TextView(context);
            tv.setText("我是第" + i + "个TextView");
           tv.setBackgroundColor(Color.BLUE);
            tv.setTextSize(18f);
            tv.setTextColor(Color.RED);
            tv.setPadding(10, 10, 10, 10);
            tv.setVisibility(View.VISIBLE);
            flowerLayout.addView(tv);

        }
        container.addView(flowerLayout);
    }
}
