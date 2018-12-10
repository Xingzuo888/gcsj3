package com.example.administrator.gongchensheji3;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei.FragmentPagerWei;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private LinearLayout wei;
    private LinearLayout tang;
    private LinearLayout bottom;
    private FragmentPagerWei fragmentWei;
    private Fragment2 tangFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建标题
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //创建侧滑菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();//初始化布局

        if (savedInstanceState == null) {
            showWei();
        }else {
            fragmentWei = (FragmentPagerWei) getSupportFragmentManager().findFragmentByTag("fragmentWei");
            tangFragment = (Fragment2) getSupportFragmentManager().findFragmentByTag("tangFragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    public void initView() {
        bottom = (LinearLayout) findViewById(R.id.bottom);
        wei = (LinearLayout) findViewById(R.id.bottom_1);
        tang = (LinearLayout) findViewById(R.id.bottom_2);
        wei.setOnClickListener(this);
        tang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_1:
                showWei();
                tang.setBackgroundColor(Color.WHITE);
                wei.setBackgroundColor(Color.BLUE);
                break;
            case R.id.bottom_2:
                showTang();
                wei.setBackgroundColor(Color.WHITE);
                tang.setBackgroundColor(Color.BLUE);
                break;
        }
    }

    public void showWei() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragmentWei == null) {
            fragmentWei = new FragmentPagerWei();
            ft.add(R.id.fl, fragmentWei,"weiFragment");
        }
        hideFrag(ft);
        ft.show(fragmentWei);
        ft.commit();
    }

    public void showTang() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tangFragment == null) {
            tangFragment = new Fragment2();
            ft.add(R.id.fl, tangFragment,"tangFragment");
        }
        hideFrag(ft);
        ft.show(tangFragment);
        ft.commit();
    }

    private void hideFrag(FragmentTransaction ft) {
        if (fragmentWei != null) {
            ft.hide(fragmentWei);
        }
        if (tangFragment != null) {
            ft.hide(tangFragment);
        }
    }


}
