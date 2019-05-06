package com.hellom.petserviceandroid.base;

import android.app.Activity;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class LoadingCallBack<T extends ResponseBean> extends JsonCallback<T> {
    private QMUITipDialog dialog;
    private Activity activity;
    private Callback<T> callback;
    private boolean needLoading;

    public LoadingCallBack(Class<T> clazz, Activity activity, Callback<T> callback, boolean needLoading) {
        super(clazz);
        this.activity = activity;
        this.callback = callback;
        this.needLoading = needLoading;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (activity != null && !activity.isFinishing()) {
            if (needLoading) {
                initLoading(activity);
            }
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (activity != null && !activity.isFinishing()) {
            int code = response.body().getCode();
            if (code == 0) {
                ToastUtils.showShort(response.body().getMsg());
                if (callback != null) {
                    callback.onError();
                }
            } else if (code == 1) {
                if (callback != null) {
                    callback.onSuccess(response.body());
                }
            }
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (callback != null) {
            callback.onError();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        stopLoading();
    }

    private void initLoading(Activity activity) {
        dialog = new QMUITipDialog.Builder(activity).setTipWord("请稍候...").setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .create();
        dialog.show();
    }

    private void stopLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public interface Callback<T> {
        void onSuccess(T response);

        void onError();
    }
}
