package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.weight.GifView;

public class GifTestActivity extends AppCompatActivity {

    @Bind(R.id.gif)
    GifView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_test);
        ButterKnife.bind(this);
        gif.setMovieResource();
    }
}
