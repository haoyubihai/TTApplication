package test.vko.cn.ttapplication.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by JiaRH on 2015/11/4.
 */
public class PacManView extends View {
    public static final int DEFAULT_SIZE = 100;
    Paint mPaint;
    int color = Color.parseColor("#998822");
    float degree1, degree2;
    private float translateX;
    private int alpha = 0;

    public PacManView(Context context) {
        this(context, null);
    }

    public PacManView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PacManView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(degree1);
        mPaint.setAlpha(255);
        RectF rectF = new RectF(-x / 1.5f, -y / 1.5f, x / 1.5f, y / 1.5f);
        canvas.drawArc(rectF, 0, 270, true, mPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(degree2);
        mPaint.setAlpha(255);
        RectF rectF2 = new RectF(-x / 1.5f, -y / 1.5f, x / 1.5f, y / 1.5f);
        canvas.drawArc(rectF2, 90, 270, true, mPaint);
        canvas.restore();

        float radius = getWidth() / 11;
        mPaint.setAlpha(alpha);
        canvas.drawCircle(translateX, getHeight() / 2, radius, mPaint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        float startT = getWidth() / 11;
        ValueAnimator translationAnim = ValueAnimator.ofFloat(getWidth() - startT, getWidth() / 2);
        translationAnim.setDuration(650);
        translationAnim.setInterpolator(new LinearInterpolator());
        translationAnim.setRepeatCount(-1);
        translationAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateX = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        translationAnim.start();
        ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 122);
        alphaAnim.setDuration(650);
        alphaAnim.setRepeatCount(-1);
        alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (Integer) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        alphaAnim.start();

        ValueAnimator rotateAnim1 = ValueAnimator.ofFloat(0, 45, 0);
        rotateAnim1.setDuration(650);
        rotateAnim1.setRepeatCount(-1);
        rotateAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree1 = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotateAnim1.start();

        ValueAnimator rotateAnim2 = ValueAnimator.ofFloat(0, -45, 0);
        rotateAnim2.setDuration(650);
        rotateAnim2.setRepeatCount(-1);
        rotateAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree2 = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotateAnim2.start();
    }
}
