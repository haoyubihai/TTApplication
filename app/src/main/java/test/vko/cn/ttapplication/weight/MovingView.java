package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by JiaRH on 2015/11/12.
 */
public class MovingView extends FrameLayout {
    private int NumberOfWave=3;
    private final  int DISTANCE=50;
    public MovingView(Context context) {
        super(context);
        init(context);
    }



    public MovingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        for (int i=0;i<getNumberOfWave();i++){
            WaveView wv = new WaveView(context);
            wv.setUpDownDistance(i *(int)(DISTANCE*Math.random()));
            if (i==0){
                wv.setColor(Color.RED);
                wv.setSPEED(1.0f);

            }else if (i==1){
                wv.setColor(Color.BLUE);
                wv.setSPEED(1.5f);
            }else if(i==2){
                wv.setColor(Color.GREEN);
                wv.setSPEED(2.0f);
            }else if(i==3){
                wv.setColor(Color.GREEN);
                wv.setSPEED(2.5f);
            }else if(i==4){
                wv.setColor(Color.GREEN);
                wv.setSPEED(2.8f);
            }
            wv.setCurrentIndex(i);
            addView(wv);
        }

    }

    public int getNumberOfWave() {
        return NumberOfWave;
    }

    public void setNumberOfWave(int numberOfWave) {
        NumberOfWave = numberOfWave;
    }
}
