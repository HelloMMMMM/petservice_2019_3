package com.hellom.petserviceandroid.base;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public abstract class JsonCallback<T extends ResponseBean> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) {
            data = gson.fromJson(jsonReader, type);
        } else if (clazz != null) {
            data = gson.fromJson(jsonReader, clazz);
        }
        return data;
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if (exception != null) {
            exception.printStackTrace();
        }
        if (exception instanceof UnknownHostException) {
            ToastUtils.showShort("网络连接失败");
        } else if (exception instanceof ConnectException) {
            ToastUtils.showShort("与服务器连接失败");
        } else if (exception instanceof SocketTimeoutException) {
            ToastUtils.showShort("网络请求超时");
        } else if (exception instanceof HttpException) {
            ToastUtils.showShort("服务器错误");
        }
    }
}
