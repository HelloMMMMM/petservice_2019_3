package com.hellom.petserviceandroid.store;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.article.ArticleFragment;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.goods.GoodsFragment;
import com.hellom.petserviceandroid.my.MyFragment;
import com.hellom.petserviceandroid.selection.SelectionFragment;
import com.hellom.petserviceandroid.service.ServiceFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends BaseActivity {
    private QMUITabSegment mTabSegment;
    private ViewPager mContentViewPager;

    private int storeId;

    @Override
    public void initView() {
        mTabSegment = findViewById(R.id.tabSegment);
        mContentViewPager = findViewById(R.id.contentViewPager);

        storeId = getIntent().getIntExtra("storeId", 0);

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("storeName"));
    }

    @Override
    public void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void initData() {
        initTabAndPager();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_store;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private void initTabAndPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GoodsFragment.newInstance(storeId));
        fragments.add(ServiceFragment.newInstance(storeId));
        fragments.add(ArticleFragment.newInstance(storeId));
        fragments.add(SelectionFragment.newInstance(storeId));
        StorePagerAdapter mPagerAdapter = new StorePagerAdapter(getSupportFragmentManager(), fragments);
        mContentViewPager.setAdapter(mPagerAdapter);

        mTabSegment.addTab(new QMUITabSegment.Tab("商品"));
        mTabSegment.addTab(new QMUITabSegment.Tab("服务"));
        mTabSegment.addTab(new QMUITabSegment.Tab("文章"));
        mTabSegment.addTab(new QMUITabSegment.Tab("评选"));

        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {

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
}
