package com.hellom.petserviceandroid.goods;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class GoodsListBean extends ResponseBean {

    private List<GoodsBean> goodsList;

    public List<GoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsBean {
        /**
         * goodsImg : http://192.168.0.6:9001/upload\temp\download (2).jpg
         * goodsName : 卫龙辣条
         * goodsPrice : 1999.0
         * goodsType : 零食
         * id : 15
         * storeId : 6
         */

        private String goodsImg;
        private String goodsName;
        private double goodsPrice;
        private String goodsType;
        private int id;
        private int storeId;

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }
}
