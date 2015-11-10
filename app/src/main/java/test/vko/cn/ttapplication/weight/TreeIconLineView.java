package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import test.vko.cn.ttapplication.R;

/**
 * Created by JiaRH on 2015/10/21.
 */
public class TreeIconLineView extends View {

    public static final int SHOW_ICON_DONWLINE = 0;
    public static final int SHOW_ICON_UPLINE = 1;
    public static final int SHOW_ICON_ALLLINE = 2;
    public static final int SHOW_ICON_ALONG = 3;
    public static final int SHOW_LINE_ALONG = 4;

    private int CURRENT_STATE = -1;
    private Bitmap centerIcon;
    private int lineColor = Color.parseColor("#0000ff");
    private float lineWith = 3.0f;
    //中心点的坐标
    private int centerX, centerY;

    private Paint mPaint;

    private Bitmap b;

    public TreeIconLineView(Context context) {
        this(context, null);
    }

    public TreeIconLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeIconLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (CURRENT_STATE) {
            case SHOW_ICON_ALLLINE:
                drawIconAllLine(canvas);
                break;
            case SHOW_ICON_DONWLINE:
                drawIconDownLine(canvas);
                break;
            case SHOW_ICON_UPLINE:
                drawIconUpLine(canvas);
                break;
            case SHOW_ICON_ALONG:
                drawIconAlong(canvas);
                break;
            case SHOW_LINE_ALONG:
                drawlineAlong(canvas);
                break;
            default:
                drawlineAlong(canvas);
                break;
        }
        invalidate();
    }


    private void drawlineAlong(Canvas canvas) {
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);
    }

    private void drawIconAlong(Canvas canvas) {

        canvas.drawBitmap(b, getWidth() / 2 - b.getWidth() / 2, getHeight() / 2 - b.getHeight() / 2, mPaint);
    }

    private void drawIconUpLine(Canvas canvas) {
        drawUpLine(canvas);
        drawIconAlong(canvas);
    }

    private void drawUpLine(Canvas canvas) {
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight() / 2, mPaint);
    }

    private void drawDownLine(Canvas canvas) {
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight(), mPaint);
    }

    private void drawIconDownLine(Canvas canvas) {
        drawDownLine(canvas);
        drawIconAlong(canvas);
    }

    private void drawIconAllLine(Canvas canvas) {
        drawlineAlong(canvas);
        drawIconAlong(canvas);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWith);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.my_ico_star);
    }

    public int getCURRENT_STATE() {
        return CURRENT_STATE;
    }

    public void setCURRENT_STATE(int CURRENT_STATE) {
        this.CURRENT_STATE = CURRENT_STATE;
    }

    public Bitmap getCenterIcon() {
        return centerIcon;
    }

    public void setCenterIcon(Bitmap centerIcon) {
        this.centerIcon = centerIcon;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public float getLineWith() {
        return lineWith;
    }

    public void setLineWith(float lineWith) {
        this.lineWith = lineWith;
    }

    public Bitmap getB() {
        return b;
    }

    public void setB(Bitmap b) {
        this.b = b;
    }
}
