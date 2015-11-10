package test.vko.cn.ttapplication.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.TreeIconLineView;

public class TreeIconLineActivity extends AppCompatActivity {

    @Bind(R.id.btn_change)
    Button btnChange;


    @Bind(R.id.tree_view)
    TreeIconLineView treeView;
    int i = -1;
    Bitmap bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_icon_line);
        ButterKnife.bind(this);
        bb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @OnClick(R.id.btn_change)
    public void change() {
        i++;
        if (i > 4) {
            i = -1;
        }
        treeView.setB(bb);
        treeView.setCURRENT_STATE(i);
        btnChange.setText(i + "");
    }
}
