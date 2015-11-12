package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import test.vko.cn.ttapplication.commonutils.DpUtils;
import test.vko.cn.ttapplication.commonutils.GeometryUtil;

/**
 * Created by JiaRH on 2015/11/11.
 */
public class ShipMovingView extends View {
    /**
     * 振幅
     */
    public static final int SWING_DISTANCE = 100;
    private Paint mPaint;
    private int color = Color.parseColor("#378dcc");
    private float waveWidth = 2.0f;
    /**
     * 存放起始点
     */
    private PointF[] startPointFs, endPointFs;
    private PointF anchorPointF1;
    private PointF anchorPointF2;
    private int screenWith, screenHeight;
    private Path mPath;

    public ShipMovingView(Context context) {
        this(context, null);
    }

    public ShipMovingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShipMovingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initPaint();
        initData(context);

    }


    private void initData(Context context) {
        screenWith = DpUtils.ScreenWidth(context);
        screenHeight = DpUtils.ScreenHeight(context);
    }


    private void calculate(int startX, int midX) {
        //起点控制
        startPointFs = new PointF[3];
        startPointFs[0] = new PointF(startX, screenHeight / 2 - SWING_DISTANCE);
        startPointFs[1] = new PointF(startX, screenHeight / 2);
        startPointFs[2] = new PointF(startX, screenHeight / 2 + SWING_DISTANCE);
        //终点控制
        endPointFs = new PointF[3];
        endPointFs[0] = new PointF(midX, screenHeight / 2 - SWING_DISTANCE);
        endPointFs[1] = new PointF(midX, screenHeight / 2);
        endPointFs[2] = new PointF(midX, screenHeight / 2 + SWING_DISTANCE);

        PointF middlePoint = GeometryUtil.getMiddlePoint(startPointFs[0], endPointFs[0]);
        anchorPointF1 = GeometryUtil.getMiddlePoint(startPointFs[0], middlePoint);
        anchorPointF1.x += SWING_DISTANCE;
        anchorPointF1.y -= SWING_DISTANCE;
        anchorPointF2 = GeometryUtil.getMiddlePoint(middlePoint, endPointFs[0]);
        anchorPointF2.y += SWING_DISTANCE;
        anchorPointF2.x -= SWING_DISTANCE;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(waveWidth);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
        canvas.restore();
        setPontFsAndPath(0, screenWith / 2);
        drawMovePath(canvas);
        setPontFsAndPath(screenWith / 2, screenWith);
        drawMovePath(canvas);

    }

    private void drawMovePath(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    private void setPontFsAndPath(int startX, int midX) {
        calculate(startX, midX);
        initPath();
    }

    private void initPath() {
        mPath = new Path();
        mPath.moveTo(startPointFs[0].x, startPointFs[0].y);
        mPath.cubicTo(anchorPointF1.x, anchorPointF1.y, anchorPointF2.x, anchorPointF2.y, endPointFs[0].x, endPointFs[0].y);
    }

}
