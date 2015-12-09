package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import test.vko.cn.ttapplication.R;

/**
 * Created by JiaRH on 2015/12/9.
 */
public class ProgressBarView extends View {

    Bitmap bp;
    /*总大小*/
    private float max = 100;
    /*当前进度*/
    private float currentPosition = 30;
    /*背景框*/
    private RectF rectF;
    /*实体*/
    private RectF solidRectF;
    /*进度条高度*/
    private int pbHeight = 15;
    /*圆弧半径*/
    private int rx = 10;
    private Paint mPaint;
    /*边框色*/
    private int colorBorder = Color.parseColor("#696969");
    /*背景色*/
    private int bgColor = Color.parseColor("#378dcc");
    private int paddingLeft, paddingRight;
    private float bpLeft;
    private float height,width;

    public ProgressBarView(Context context) {
        this(context, null);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.hq);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);

        canvas.drawBitmap(bp, bpLeft - rx>paddingLeft? bpLeft - rx:paddingLeft, 0, mPaint);
    }

    private void drawBg(Canvas canvas) {
        mPaint.setColor(colorBorder);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, rx, rx, mPaint);
        mPaint.setColor(bgColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(solidRectF, rx, rx, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, bp.getHeight());
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        height = getHeight();
        rectF = new RectF(paddingLeft, height - pbHeight, getWidth() - paddingLeft - paddingRight, height);
        bpLeft = currentPosition / max * rectF.width()+paddingRight;
        solidRectF = new RectF(paddingLeft, getHeight() - pbHeight, bpLeft, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    public void setBp(Bitmap bp) {
        this.bp = bp;
    }


    public void setMax(float max) {
        this.max = max;
    }

    public void setCurrentPosition(float currentPosition) {
        this.currentPosition = currentPosition;
        if (currentPosition == 0)
            currentPosition = 1;
        invalidate();
    }

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
    }


    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }


    public void setRx(int rx) {
        this.rx = rx;
    }
}
