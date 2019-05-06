package com.hellom.petserviceandroid.buyvip;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class VipListBean extends ResponseBean {

    private List<VipsBean> vips;

    public List<VipsBean> getVips() {
        return vips;
    }

    public void setVips(List<VipsBean> vips) {
        this.vips = vips;
    }

    public static class VipsBean {
        /**
         * desc : 1.包含基础卡优惠
         2.神秘服务
         * id : 3
         * name : 贵宾卡
         * price : 199.0
         */

        private String desc;
        private int id;
        private String name;
        private double price;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
