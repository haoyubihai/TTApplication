package test.vko.cn.ttapplication.weight.recyclerhelper;

import android.graphics.Path;

/**
 * Created by JiaRH on 2015/11/19.
 */
public class WavePath extends Path {
    public enum WaveType{
        UP,LEVEL,BOTTOM
    }
    WaveType waveType;

    public WaveType getWaveType() {
        return waveType;
    }

    public WavePath setWaveType(WaveType waveType) {
        this.waveType = waveType;
        return  this;
    }
}
