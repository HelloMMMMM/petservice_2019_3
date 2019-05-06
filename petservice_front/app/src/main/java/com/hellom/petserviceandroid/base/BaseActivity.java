package com.hellom.petserviceandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        if (getContentViewId() != 0) {
            setContentView(getContentViewId());
        }
        initView();
        initListener();
        initData();
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract int getContentViewId();
}
