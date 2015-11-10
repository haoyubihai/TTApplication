package test.vko.cn.ttapplication.activitys;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.vko.cn.ttapplication.R;

public class NavigationActivity extends AppCompatActivity {

    @Bind(R.id.id_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.id_content)
    TextView mContent;
    @Bind(R.id.id_drawer_lay)
    DrawerLayout mDrawerLay;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);

        mToolbar.setTitle("NavigationTest");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("NavigationTest");

        setUpContentView(navigationView);
    }

    private void setUpContentView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            private MenuItem mPreItem;

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (mPreItem != null) mPreItem.setChecked(false);
                menuItem.setCheckable(true);
                mDrawerLay.closeDrawers();
                mPreItem = menuItem;
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_draw, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLay.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
