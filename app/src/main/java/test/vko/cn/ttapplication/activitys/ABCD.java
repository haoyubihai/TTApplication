package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.vko.cn.ttapplication.R;

public class ABCD extends AppCompatActivity {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    String ss = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcd);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void onAclicked() {
        add("a");
    }

    @OnClick(R.id.button2)
    public void onBclicked() {
        add("b");
    }

    @OnClick(R.id.button3)
    public void onCclicked() {
        add("c");
    }

    @OnClick(R.id.button4)
    public void onDclicked() {
        add("d");
    }

    private void add(String a) {
        if (!TextUtils.isEmpty(a)) {
            if (TextUtils.isEmpty(ss)) {
                ss += a;
            } else {
                if (ss.contains(a)) {
                    ss = ss.replace(a, "");
                } else {
                    ss += a;
                }
            }
        }
        System.out.println(ss.trim() + "&&&&&&");

    }
}
