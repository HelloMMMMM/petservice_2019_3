package com.hellom.petserviceandroid.storelist;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class StoreListBean  extends ResponseBean {


    private List<StoreBean> storeList;

    public List<StoreBean> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreBean> storeList) {
        this.storeList = storeList;
    }

    public static class StoreBean {
        /**
         * id : 4
         * phone : 12345678910
         * qq : 1694327880
         * storeAddress : 湖北武汉
         * storeName : 测试店铺
         * storeStar : 0
         * storeType : 1
         */

        private int id;
        private String phone;
        private String qq;
        private String storeAddress;
        private String storeName;
        private int storeStar;
        private int storeType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getStoreStar() {
            return storeStar;
        }

        public void setStoreStar(int storeStar) {
            this.storeStar = storeStar;
        }

        public int getStoreType() {
            return storeType;
        }

        public void setStoreType(int storeType) {
            this.storeType = storeType;
        }
    }
}
