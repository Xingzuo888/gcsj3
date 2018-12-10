package com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */

public class FragmentPagerWeiAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public FragmentPagerWeiAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
