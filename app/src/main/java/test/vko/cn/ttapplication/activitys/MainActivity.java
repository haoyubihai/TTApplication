package test.vko.cn.ttapplication.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.vko.cn.ttapplication.R;
import test.vko.cn.ttapplication.commonutils.ThemeUtil;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.go_back)
    Button goBack;
    @Bind(R.id.snack_bar)
    Button snackBar;
    @Bind(R.id.et_edit)
    EditText etEdit;
    @Bind(R.id.text_lay)
    TextInputLayout textLay;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tab_lay)
    TabLayout tabLay;
    @Bind(R.id.btn_nav)
    Button btnNav;
    @Bind(R.id.btn_coor)
    Button btnCoor;
    @Bind(R.id.btn_loadSougou)
    Button btnLoadSougou;
    @Bind(R.id.btn_load_gif)
    Button btnLoadGif;
    @Bind(R.id.btn_star_view)
    Button btnStarView;
    @Bind(R.id.btn_abcd_view)
    Button btnAbcdView;
    @Bind(R.id.btn_tree_view)
    Button btnTreeView;
    @Bind(R.id.btn_recycler_view)
    Button btnRecyclerView;
    @Bind(R.id.btn_shape_view)
    Button btnShapeView;
    @Bind(R.id.btn_avLoading_view)
    Button btnAvLoadingView;
    @Bind(R.id.btn_observer_view)
    Button btnObserverView;
    @Bind(R.id.btn_canvas_view)
    Button btnCanvasView;
    @Bind(R.id.btn_benzier_view)
    Button btnBenzierView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ThemeUtil.setThemeTitleBar(this);
        initData();

    }

    private void initView() {

        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintEnabled(true);
        systemBarTintManager.setTintColor(R.color.colorPrimary);

    }

    private void initData() {
        textLay.setHint("userName");
        tabLay.addTab(tabLay.newTab().setText("java"));
        tabLay.addTab(tabLay.newTab().setText("c++"));
        tabLay.addTab(tabLay.newTab().setText("php"));
        tabLay.addTab(tabLay.newTab().setText("c"));
        tabLay.addTab(tabLay.newTab().setText("ObjectC"));
        tabLay.addTab(tabLay.newTab().setText("javaScript"));
        tabLay.addTab(tabLay.newTab().setText("python"));
        tabLay.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLay.setSmoothScrollingEnabled(true);
    }

    @OnClick(R.id.go_back)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.snack_bar)
    public void onSnackBarClick() {
        showInfo();
    }

    @OnClick(R.id.fab)
    public void onFABClick() {
        showInfo();
    }

    @OnClick(R.id.btn_nav)
    public void onNavClick() {

        Intent i = new Intent(this, NavigationActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_coor)
    public void onCoorClick() {

        Intent i = new Intent(this, CoordinatorActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_loadSougou)
    public void onLoadSouGouClick() {

        Intent i = new Intent(this, LoadingOfSouGouActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_load_gif)
    public void onLoadGifClick() {

        Intent i = new Intent(this, GifTestActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_star_view)
    public void onStarClick() {

        Intent i = new Intent(this, StarActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_abcd_view)
    public void onABCDClick() {

        Intent i = new Intent(this, ABCD.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_tree_view)
    public void onTreeClick() {

        Intent i = new Intent(this, TreeIconLineActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_recycler_view)
    public void onRecyclerClick() {

        Intent i = new Intent(this, RecycleViewActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_shape_view)
    public void onShapeClick() {

        Intent i = new Intent(this, ShapeDrawableActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_avLoading_view)
    public void onAvloadingClick() {

        Intent i = new Intent(this, AVLoadingIndicatorViewActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_observer_view)
    public void onObserverClick() {

        Intent i = new Intent(this, ObserverActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_canvas_view)
    public void onCanvasClick() {

        Intent i = new Intent(this, CanvasTestActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_benzier_view)
    public void onBenZierClick() {

        Intent i = new Intent(this, BenzierActivity.class);
        startActivity(i);
    }


    public void showInfo() {
        Snackbar.make(snackBar, "hello test", Snackbar.LENGTH_SHORT).setAction("back", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
