package test.vko.cn.ttapplication.activitys;

import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.commonutils.DpUtils;

public class ShapeDrawableActivity extends AppCompatActivity {

    private static final int CIRCLE_RADIUS_DP = 50;
    @Bind(R.id.icon_img)
    ImageView iconImg;
    @Bind(R.id.float_view)
    View floatView;
    private int radius = 660;
    private ShapeDrawable overLayDrawable;
    private int ViewHeight = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_drawable);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        floatView.setBackground(overLayDrawable);
    }

    private void initData() {

        overLayDrawable = getOverLayDrawable();
    }

    public ShapeDrawable getOverLayDrawable() {

        RectF rectF = new RectF(
                DpUtils.ScreenWidth(this) / 2 - DpUtils.dp2px(this, getCircleRadiusDp() * 2),
                ViewHeight / 2 - DpUtils.dp2px(this, getCircleRadiusDp()),
                DpUtils.ScreenWidth(this) / 2 - DpUtils.dp2px(this, getCircleRadiusDp() * 2),
                ViewHeight / 2 - DpUtils.dp2px(this, getCircleRadiusDp()));
        RoundRectShape mShape = new RoundRectShape(null, rectF, new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        ShapeDrawable overlay = new ShapeDrawable(mShape);
        overlay.getPaint().setColor(getResources().getColor(R.color.colorAccent));

        return overlay;
    }

    /*  private ShapeDrawable buildAvatarCircleOverlay() {
          int radius = 666;
          ShapeDrawable overlay = new ShapeDrawable(new RoundRectShape(null,
                  new RectF(
                          sScreenWidth / 2 - dpToPx(getCircleRadiusDp() * 2),
                          sProfileImageHeight / 2 - dpToPx(getCircleRadiusDp() * 2),
                          sScreenWidth / 2 - dpToPx(getCircleRadiusDp() * 2),
                          sProfileImageHeight / 2 - dpToPx(getCircleRadiusDp() * 2)),
                  new float[]{radius, radius, radius, radius, radius, radius, radius, radius}));
          overlay.getPaint().setColor(getResources().getColor(R.color.gray));

          return overlay;
      }*/
    public int getCircleRadiusDp() {
        return CIRCLE_RADIUS_DP;

    }
}
