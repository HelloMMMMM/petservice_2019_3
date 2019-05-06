package com.hellom.petserviceandroid.login;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.home.HomeActivity;
import com.hellom.petserviceandroid.register.RegisterActivity;
import com.hellom.petserviceandroid.util.SharePreferenceUtil;
import com.lzy.okgo.OkGo;

public class LoginActivity extends BaseActivity {
    private EditText userName;
    private EditText password;

    @Override
    public void initView() {
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
    }

    @Override
    public void initListener() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.to_register).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.to_register:
                jumpToRegister();
                break;
            default:
                break;
        }
    }

    private void jumpToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        if (!checkInput()) {
            return;
        }
        OkGo.<LoginBean>post(Constant.ROOT_URL + Constant.LOGIN).params("userName", userName.getText().toString())
                .params("password", password.getText().toString()).execute(new LoadingCallBack<>(LoginBean.class, this, new LoadingCallBack.Callback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) {
                saveAccount(response);
                jumpToStoreList();
            }

            @Override
            public void onError() {

            }
        }, true));
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(password.getText().toString())
                || userName.getText().toString().length() < 8 || password.getText().toString().length() < 6) {
            ToastUtils.showShort("请将按要求信息填写完整");
            return false;
        }
        return true;
    }

    private void saveAccount(LoginBean loginBean) {
        SharePreferenceUtil.saveAccount(userName.getText().toString(), password.getText().toString());
        SharePreferenceUtil.savePersonalInfo(loginBean);
    }

    private void jumpToStoreList() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
