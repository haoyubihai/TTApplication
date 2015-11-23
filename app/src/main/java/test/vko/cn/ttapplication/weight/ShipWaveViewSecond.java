package test.vko.cn.ttapplication.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import test.vko.cn.ttapplication.R;

/**
 * Created by JiaRH on 2015/11/19.
 */
public class ShipWaveViewSecond extends View {
    Paint mPaint;
    private int color = Color.parseColor("#378DCC");

    private int viewWidth;
    private int viewHeight;
    private final int WAVE_HEIGHT = 20;
    private List<PointF> mPointsList;
    private int mWaveWidth = 300;
    private int mLevelLine;
    private Path mWavePath;
    private boolean isMeasured;
    /**
     * 记录当前运动点的位置
     */
    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeature;
    Bitmap boat;
    ValueAnimator valueAnimator;
    private int speed = 2;
    private int moveLen = 0;

    private float currentCircleX, currentCircleY;
    private float radius = 5;
    ValueAnimator circleValue;

    public ShipWaveViewSecond(Context context) {
        this(context, null);
    }

    public ShipWaveViewSecond(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShipWaveViewSecond(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
        initPaint();
        postDelayed(new Runnable() {
            @Override
            public void run() {

                startBallAni();
                startCircleUp();

            }
        }, 300);
    }

    private void initData(Context context) {

        mPointsList = new ArrayList<>();
        boat = BitmapFactory.decodeResource(getResources(), R.drawable.earch);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStrokeWidth(1.5f);
        mPaint.setStyle(Paint.Style.STROKE);
        mWavePath = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath.reset();
        mWavePath.moveTo(mPointsList.get(0).x, mPointsList.get(0).y);
        for (int i = 0; i < mPointsList.size() - 2; i = i + 2) {
            mWavePath.quadTo(mPointsList.get(i + 1).x,
                    mPointsList.get(i + 1).y, mPointsList.get(i + 2)
                            .x, mPointsList.get(i + 2).y);
        }

//        mWavePath.lineTo(viewWidth, 0);
//        mWavePath.lineTo(0, 0);
//        mWavePath.close();
        mPathMeature = new PathMeasure(mWavePath, true);
        drawPath(canvas);
        drawBoat(canvas);
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(currentCircleX, currentCircleY, radius, mPaint);
    }

    private void drawPath(Canvas canvas) {
        mPaint.setColor(Color.TRANSPARENT);
        // mPaint的Style是FILL，会填充整个Path区域
        canvas.drawPath(mWavePath, mPaint);
    }

    private void drawBoat(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        if (mCurrentPosition[0] > viewWidth) {
            mCurrentPosition[0] = viewWidth;
            resetAni();
        }
        canvas.drawBitmap(boat, mCurrentPosition[0], mCurrentPosition[1], mPaint);
    }

    private void resetAni() {

        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
            startBallAni();
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        viewWidth = getWidth();
        viewHeight = getHeight();
        mLevelLine = viewHeight / 2 - 80;
        currentCircleX = (float) Math.floor(Math.random() * viewWidth);
        currentCircleY = mLevelLine;
        if (!isMeasured) {

            isMeasured = true;
            int n = (int) Math.round(viewWidth / mWaveWidth + 0.5);
//            n=1;
            // n个波形需要4n+1个点，但是我们要预留一个波形在左边隐藏区域，所以需要4n+5个点
            for (int i = 0; i < (4 * n + 5); i++) {
                // 从P0开始初始化到P4n+4，总共4n+5个点
//                float x = i * mWaveWidth / 4 - mWaveWidth;
                float x = i * mWaveWidth / 4;
                float y = 0;
                switch (i % 4) {
                    case 0:
                    case 2:
                        // 零点位于水位线上
                        y = mLevelLine;
                        break;
                    case 1:
                        // 往下波动的控制点
                        y = mLevelLine + WAVE_HEIGHT;
                        break;
                    case 3:
                        // 往上波动的控制点
                        y = mLevelLine - WAVE_HEIGHT;
                        break;
                }
                mPointsList.add(new PointF(x, y));
                /**
                 * 初始化小球的位置
                 */
                mCurrentPosition[0] = mPointsList.get(0).x;
                mCurrentPosition[1] = mPointsList.get(0).y;
            }
        }
    }

    private void startCircleUp() {
        circleValue = ValueAnimator.ofFloat(currentCircleY,0);
        circleValue.setDuration(10000);
        circleValue.setRepeatCount(-1);
        circleValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                currentCircleY = (float) animation.getAnimatedValue();

                invalidate();
            }
        });
        circleValue.start();
       ValueAnimator circleAlphaValue = ValueAnimator.ofInt(1,0);
        circleAlphaValue.setDuration(10000);
        circleAlphaValue.setRepeatCount(-1);
        circleAlphaValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPaint.setAlpha((int)(animation.getAnimatedValue()));
                invalidate();
            }
        });
        circleAlphaValue.start();
        ValueAnimator circleRadiusValue = ValueAnimator.ofFloat(1,25);
        circleRadiusValue.setDuration(6000);
        circleRadiusValue.setRepeatCount(-1);
        circleRadiusValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                radius= (float) animation.getAnimatedValue();
                if (radius==23){
                    mPaint.setAlpha(0);

                }
                invalidate();
            }
        });
        circleRadiusValue.start();
    }

    /**
     * 开始小球的运动
     */
    private void startBallAni() {
        valueAnimator = ValueAnimator.ofFloat(0, mPathMeature.getLength());
        valueAnimator.setDuration(25000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mPathMeature.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
