package com.hellom.petserviceandroid.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.my.MyFragment;
import com.hellom.petserviceandroid.storelist.StoreListFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private ViewPager viewPager;
    private QMUITabSegment mTabSegment;

    @Override
    public void initView() {
        mTabSegment = findViewById(R.id.tab);
        mTabSegment.addTab(new QMUITabSegment.Tab("店铺"));
        mTabSegment.addTab(new QMUITabSegment.Tab("我的"));
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        viewPager = findViewById(R.id.viewpager);
    }

    @Override
    public void initListener() {
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                viewPager.setCurrentItem(index, true);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(StoreListFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        HomePageAdapter pagerAdapter = new HomePageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        mTabSegment.setupWithViewPager(viewPager,false);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void onClick(View view) {

    }
}
