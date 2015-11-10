package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.adapter.MyRecyclerAdapter;
import test.vko.cn.ttapplication.weight.recyclerhelper.DividerGridItemDecoration;

public class RecycleViewActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<String> mDatas;

    private MyRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {


//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            mDatas.add(i + "");
        mAdapter = new MyRecyclerAdapter(this, mDatas);
    }
}
