package test.vko.cn.ttapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by JiaRH on 2015/11/25.
 */
public class RecommonTextView extends TextView {

    public RecommonTextView(Context context) {
        this(context,null);
    }

    public RecommonTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecommonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
