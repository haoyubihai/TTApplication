package test.vko.cn.ttapplication.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import test.vko.cn.ttapplication.commonutils.GeometryUtil;

/**
 * Created by JiaRH on 2015/11/6.
 */
public class BenzierView extends View {
    PointF A;//起点
    PointF B;//操作点1
    PointF C;//操作点2
    PointF D;//终点
    PointF M;//锚点
    private Paint mPaint;
    private int color = Color.parseColor("#990000");
    private Path mPath;
    private List<PointF> points;
    /**
     * 存储过圆心的直线与该圆的两个交点
     */
    private PointF[] pointFs1 = new PointF[2];
    private PointF[] pointFs2 = new PointF[2];
    private float radius = 100f;

    private boolean isStrech;
    /**
     * 记录当前运动点的位置
     */
    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeature;

    public BenzierView(Context context) {
        this(context, null);
    }

    public BenzierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BenzierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaintAndPath();
        initPointfs();
    }

    private void initPointfs() {
        A = new PointF(100f, 100f);
        B = new PointF(800f, 100f);
        C = new PointF(100f, 800f);
        D = new PointF(800f, 800f);

        if (points == null) {
            points = new ArrayList<>();

        }

        points.add(A);
        points.add(B);
        points.add(C);
        points.add(D);
        /**
         * 获取直径与圆的两个交点
         */
        pointFs1 = GeometryUtil.getIntersectionPoints(A, radius, 1d);

/**
 * 初始化小球的位置
 */
        mCurrentPosition[0] = pointFs1[1].x;
        mCurrentPosition[1] = pointFs1[1].y;

    }

    private void initPaintAndPath() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2.0f);
        mPath = new Path();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        mPath.moveTo(points.get(0).x, points.get(0).y);
//        cubicLine(canvas);
        drawBenZier();
        canvas.drawPath(mPath, mPaint);
        drawBall(canvas);
        postInvalidate();


    }

    private void drawBall(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], 30, mPaint);
    }

    private void drawBenZier() {
        pointFs2 = GeometryUtil.getIntersectionPoints(D, radius, 1d);
        mPath.reset();
        mPath.moveTo(pointFs1[1].x, pointFs1[1].y);//移动到E
//        M = GeometryUtil.getPointByPercent(pointFs1[0], pointFs2[0], .5f);//选取HG的中点为锚点
//        M = GeometryUtil.getPointByPercent(A, D, .5f);//选取AD的中点为锚点
        M = GeometryUtil.getPointByPercent(pointFs1[1], pointFs2[0], .5f);//选取EG的中点为锚点
        mPath.quadTo(M.x, M.y, pointFs2[1].x, pointFs2[1].y);//画线EF
        mPath.lineTo(pointFs2[0].x, pointFs2[0].y);//线段FG
//        M = GeometryUtil.getPointByPercent(pointFs2[0], pointFs1[1], .5f);//选取的中点为锚点
        mPath.quadTo(M.x, M.y, pointFs1[0].x, pointFs1[0].y);//画线GH
        mPath.lineTo(pointFs1[1].x, pointFs1[1].y);//线段HE


        mPathMeature = new PathMeasure(mPath, true);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int downX, downY;

        float currentx = getWidth() / 2, currentY = getHeight() / 2;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                downY = (int) event.getRawY();

                Rect rect = new Rect();
                rect.left = 0;
                rect.right = (int) radius * 2 + rect.left;
                rect.top = 0;
                rect.bottom = (int) radius * 2 + rect.top;

                isStrech = !rect.contains(downX, downY);

                break;
            case MotionEvent.ACTION_MOVE:
                if (isStrech) {
                    currentx = points.get(3).x = event.getX();
                    currentY = points.get(3).y = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                points.get(3).x = getWidth() / 2;
//                points.get(3).y = getHeight() / 2;
                startAni(currentx, currentY);
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    private void startAni(float xs, float ys) {
        ValueAnimator x = ValueAnimator.ofFloat(xs - 300, xs + 300);
        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                points.get(3).x = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        x.setRepeatMode(0);
        x.setDuration(3000);
        x.setInterpolator(new BounceInterpolator());
        x.start();
        ValueAnimator y = ValueAnimator.ofFloat(ys - 300, ys + 300);
        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                points.get(3).y = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        y.setRepeatMode(0);
        y.setDuration(3000);
        y.setInterpolator(new BounceInterpolator());
        y.start();

        startBallAni();

    }

    /**
     * 开始小球的运动
     */
    private void startBallAni() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeature.getLength());
        valueAnimator.setDuration(15000);
//        valueAnimator.setRepeatMode(valueAnimator.REVERSE);
        valueAnimator.setRepeatCount(Integer.MAX_VALUE);
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

    private void drawCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(points.get(0).x, points.get(0).y, radius, mPaint);
        canvas.drawCircle(points.get(3).x, points.get(3).y, radius, mPaint);

    }

    private void cubicLine(Canvas canvas) {
        mPath.cubicTo(points.get(1).x, points.get(1).y, points.get(2).x, points.get(2).y, points.get(3).x, points.get(3).y);
    }
}
