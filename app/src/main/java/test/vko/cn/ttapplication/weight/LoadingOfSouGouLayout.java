package test.vko.cn.ttapplication.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by JiaRH on 2015/10/14.
 */
public class LoadingOfSouGouLayout extends View {

    private Paint mPaint;
    private int radius = 10;
    private int startX, startY, endY, currentY;
    private float density;
    private RectF mRectF;
    private int mColor = Color.parseColor("#0000ff");
    private int mShadowColor = Color.parseColor("#3f3b2d");

    public LoadingOfSouGouLayout(Context context) {
        this(context, null);
    }

    public LoadingOfSouGouLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingOfSouGouLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        startX = getWidth() / 2;
        startY = getHeight() / 2;
        endY = getHeight() * 5 / 6;
        mPaint.setColor(mColor);
        if (currentY == 0) {
            startAni();
        } else {
            drawCircle(canvas);
            drawShader(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        if (endY - currentY > 10) {
            canvas.drawCircle(startX, currentY, radius * density, mPaint);
        } else {
            mRectF = new RectF(startX - radius * density - 2, currentY - radius * density + 5, startX + radius * density + 2, currentY - radius * density + 5);
            canvas.drawOval(mRectF, mPaint);
        }
    }

    private void drawShader(Canvas canvas) {
        int dx = endY - startY;
        int cdx = endY - currentY;
        float ratio = (float) (cdx * 1.0 / dx);
        if (ratio <= 0.3) return;
        mPaint.setColor(mShadowColor);
        int shadowRadius = (int) (radius * density * ratio);
        mRectF = new RectF(startX - shadowRadius, endY + 10, startX + shadowRadius, endY + 15);
        canvas.drawOval(mRectF, mPaint);
    }

    private void startAni() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(startY, endY);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentY = (Integer) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new AccelerateInterpolator(1.2f));
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(2);
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }
}
