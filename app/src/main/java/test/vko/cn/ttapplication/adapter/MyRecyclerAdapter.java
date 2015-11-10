package test.vko.cn.ttapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.vko.cn.ttapplication.R;

/**
 * Created by JiaRH on 2015/10/22.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHodler> {

    private Context context;
    private List<String> mModels;

    public MyRecyclerAdapter(Context context, List<String> mModels) {
        this.context = context;
        this.mModels = mModels;
    }


    @Override
    public MyHodler onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_view, null);

        MyHodler vh = new MyHodler(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyHodler myHodler, int i) {
        myHodler.tv.setText(mModels.get(i));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    class MyHodler extends RecyclerView.ViewHolder {


        TextView tv;

        public MyHodler(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }
}
