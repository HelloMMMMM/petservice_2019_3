package com.hellom.petserviceandroid;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.home.HomeActivity;
import com.hellom.petserviceandroid.login.LoginActivity;
import com.hellom.petserviceandroid.login.LoginBean;
import com.lzy.okgo.OkGo;


public class MainActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        checkPermission();
    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    private void jumpToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void jumpToStoreList() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

    }

    private void checkPermission() {
        if (PermissionUtils.isGranted(PermissionConstants.CAMERA, PermissionConstants.STORAGE)) {
            login();
        } else {
            PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE).callback(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    login();
                }

                @Override
                public void onDenied() {
                    ToastUtils.showShort("请授予应用必需的权限");
                    finish();
                }
            }).request();
        }
    }

    private void login() {
        boolean hasLogin = SPUtils.getInstance().getBoolean("hasLogin", false);
        if (hasLogin) {
            OkGo.<LoginBean>post(Constant.ROOT_URL + Constant.LOGIN).params("userName", SPUtils.getInstance().getString("userName"))
                    .params("password", SPUtils.getInstance().getString("password")).execute(new LoadingCallBack<>(LoginBean.class, this, new LoadingCallBack.Callback<LoginBean>() {
                @Override
                public void onSuccess(LoginBean response) {
                    jumpToStoreList();
                }

                @Override
                public void onError() {
                    jumpToLogin();
                }
            }, false));
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    jumpToLogin();
                }
            }, 1000);
        }
    }
}
