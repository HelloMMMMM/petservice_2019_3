package com.hellom.petserviceandroid.util;

import com.blankj.utilcode.util.SPUtils;
import com.hellom.petserviceandroid.login.LoginBean;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class SharePreferenceUtil {
    public static void saveAccount(String userName, String password) {
        SPUtils spUtils = SPUtils.getInstance();
        spUtils.put("hasLogin", true);
        spUtils.put("userName", userName);
        spUtils.put("password", password);
    }

    public static void logout() {
        SPUtils spUtils = SPUtils.getInstance();
        spUtils.put("hasLogin", false);
        spUtils.put("userName", "");
        spUtils.put("password", "");
        spUtils.put("id", 0);
        spUtils.put("masterAddress", "");
        spUtils.put("masterName", "");
        spUtils.put("masterPhone", "");
        spUtils.put("petAge", 0);
        spUtils.put("petKind", "");
        spUtils.put("petName", "");
        spUtils.put("petType", "");
        spUtils.put("petImg", "");
        spUtils.put("vipId",0);
    }

    public static void savePersonalInfo(LoginBean loginBean) {
        LoginBean.MasterBean masterBean = loginBean.getMaster();
        SPUtils spUtils = SPUtils.getInstance();
        spUtils.put("id", masterBean.getId());
        spUtils.put("masterAddress", masterBean.getMasterAddress());
        spUtils.put("masterName", masterBean.getMasterName());
        spUtils.put("masterPhone", masterBean.getMasterPhone());
        spUtils.put("petAge", masterBean.getPetAge());
        spUtils.put("petKind", masterBean.getPetKind());
        spUtils.put("petName", masterBean.getPetName());
        spUtils.put("petType", masterBean.getPetType());
        spUtils.put("petImg", masterBean.getPetImg());
        spUtils.put("vipId",masterBean.getVipId());
    }
}
