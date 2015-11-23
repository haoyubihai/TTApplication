package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiaRH on 2015/11/23.
 */
public class WaveMoveView extends FrameLayout {

    private List<ShipWaveView> shipWaveViews;
    private ShipWaveView swv0,swv1,swv2;
    private ShipWaveViewSecond boatView;

    public WaveMoveView(Context context) {
        super(context);
        init(context);
    }

    public WaveMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public WaveMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        shipWaveViews = new ArrayList<>();
        swv0 = new ShipWaveView(context);
        swv1 = new ShipWaveView(context);
        swv2 = new ShipWaveView(context);

        boatView = new ShipWaveViewSecond(context);
        swv0.setSpeed(5);
        swv1.setSpeed(8);
        swv2.setSpeed(10);

//        swv0.setColor(Color.BLUE);
//        swv1.setColor(Color.BLUE);
//        swv2.setColor(Color.BLUE);

        swv0.setWAVE_HEIGHT(40);
        swv1.setWAVE_HEIGHT(35);
        swv2.setWAVE_HEIGHT(50);

        swv0.setmWaveWidth(550);
        swv1.setmWaveWidth(600);
        swv2.setmWaveWidth(700);

//        swv0.startBallAni();
        shipWaveViews.add(swv0);
        shipWaveViews.add(swv1);
        shipWaveViews.add(swv2);
       for(ShipWaveView swv : shipWaveViews){
           this.addView(swv);
           swv.startMove();
       }
        this.addView(boatView);
    }
}
