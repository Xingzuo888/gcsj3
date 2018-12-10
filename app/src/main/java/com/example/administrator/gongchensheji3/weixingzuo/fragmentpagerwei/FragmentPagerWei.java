package com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gongchensheji3.R;
import com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei.fragment_wei.Fragment_wei1;
import com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei.fragment_wei.Fragment_wei2;
import com.example.administrator.gongchensheji3.weixingzuo.fragmentpagerwei.fragment_wei.Fragment_wei3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/9.
 */

public class FragmentPagerWei extends Fragment implements ViewPager.OnPageChangeListener {

    private ImageView imageView;// 动画图片
    private List<Fragment> fragmentList;
    private ViewPager pager;
    private PagerTabStrip tabStrip;
    private int currIndex = 0;// 当前页卡编号
    private int oneTabWid;
    private int offset = 0;// 动画图片偏移量
    private int bmpW;// 动画图片宽度

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_wei_layout,container,false);
        initTab(view);//初始化标题
        initPager(view);//初始化ViewPager
        return view;
    }

    public void initTab(View v) {
        TextView t1 = (TextView) v.findViewById(R.id.tv1);
        TextView t2 = (TextView) v.findViewById(R.id.tv2);
        TextView t3= (TextView) v.findViewById(R.id.tv3);
        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
    }

    public void initPager(View view) {

        //通过Fragment作为ViewPager的数据源
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_wei1());
        fragmentList.add(new Fragment_wei2());
        fragmentList.add(new Fragment_wei3());

        //初始化ViewPager
        pager = (ViewPager) view.findViewById(R.id.pager_wei);

        imageView= (ImageView) view.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(),R.drawable.yellow).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        oneTabWid = screenW/3;
        offset = (oneTabWid-bmpW)/2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset,0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
        pager.setCurrentItem(0);

        pager.setOnPageChangeListener(this);// 若不设置这个，标题下边的线不会随着页卡滑动而滑动

        //创建FragmentPagerWeiAdapter的适配器
        FragmentPagerWeiAdapter adapter = new FragmentPagerWeiAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        pager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = new TranslateAnimation(oneTabWid * currIndex, oneTabWid * position, 0, 0);
        currIndex = position;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(500);
        imageView.startAnimation(animation);
        Toast.makeText(getContext(), "您选了 "+(position+1)+"页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyOnClickListener implements View.OnClickListener{
        private int index = 0;

        public MyOnClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            pager.setCurrentItem(index);
        }
    }
}
