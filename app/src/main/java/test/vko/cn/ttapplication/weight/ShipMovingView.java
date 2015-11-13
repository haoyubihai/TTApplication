package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

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
    /**
     * 记录当前运动点的位置
     */
    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeasure;

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
        mCurrentPosition[0]=0;
        mCurrentPosition[1]=screenHeight/2;

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
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
//        canvas.restore();
//        drawCircle(canvas);
        mPath.reset();
//        setPontFsAndPath(-screenWith / 2, 0);
//        drawMovePath(canvas);
        setPontFsAndPath(0, screenWith / 2);
        drawMovePath(canvas);
        setPontFsAndPath(screenWith / 2, screenWith);
        drawMovePath(canvas);
        mPathMeasure = new PathMeasure(mPath,true);
        drawCircle(canvas);
//        move();
//        startBallAni();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startBallAni();
                break;
        }
        return true;
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], 30, mPaint);
        invalidate();
    }

    private void drawMovePath(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        invalidate();
    }

    private void setPontFsAndPath(int startX, int midX) {
        calculate(startX, midX);
        initPath();
    }

    private void initPath() {

        mPath.moveTo(startPointFs[0].x, startPointFs[0].y);
        mPath.cubicTo(anchorPointF1.x, anchorPointF1.y, anchorPointF2.x, anchorPointF2.y, endPointFs[0].x, endPointFs[0].y);
    }

    private void move(){
        ObjectAnimator moveAni = ObjectAnimator.ofFloat(this,"translationX",0,screenWith/2);
        moveAni.setDuration(5000);
        moveAni.setRepeatCount(Integer.MAX_VALUE);
        moveAni.setRepeatMode(ValueAnimator.RESTART);
        moveAni.setInterpolator(new LinearInterpolator());
        moveAni.start();
    }
    /**
     * 开始小球的运动
     */
    private void startBallAni() {
        android.animation.ValueAnimator valueAnimator = android.animation.ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(15000);
//        valueAnimator.setRepeatMode(valueAnimator.REVERSE);
        valueAnimator.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(android.animation.ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
