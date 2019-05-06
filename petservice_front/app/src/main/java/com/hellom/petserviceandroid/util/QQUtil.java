package com.hellom.petserviceandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class QQUtil {
    private static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

    public static void toChat(Activity activity, String qq) {
        if (isQQClientAvailable(activity)) {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl));
            if (isValidIntent(activity, intent)) {
                activity.startActivity(intent);
            }
        } else {
            ToastUtils.showShort("请安装QQ客户端");
        }
    }
}
