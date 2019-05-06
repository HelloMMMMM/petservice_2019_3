package com.hellom.petserviceandroid.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hellom.petserviceandroid.R;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class GlideUtil {
    private static RequestOptions getDefaultOptions() {
        return new RequestOptions().placeholder(R.drawable.drawable_splash).error(R.drawable.drawable_splash);
    }

    public static void load(Context context, Uri uri, ImageView target) {
        Glide.with(context).load(uri).apply(getDefaultOptions()).into(target);
    }

    public static void load(Context context, String url, ImageView target) {
        Glide.with(context).load(url).apply(getDefaultOptions()).into(target);
    }
}
