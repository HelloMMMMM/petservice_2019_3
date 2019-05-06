package com.hellom.petserviceandroid.orderlist;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class OrderListBean extends ResponseBean {

    private List<OrderBeansBean> orderBeans;

    public List<OrderBeansBean> getOrderBeans() {
        return orderBeans;
    }

    public void setOrderBeans(List<OrderBeansBean> orderBeans) {
        this.orderBeans = orderBeans;
    }

    public static class OrderBeansBean {
        /**
         * isAccept : 2
         * master : {"id":1,"masterAddress":"中国上海","masterName":"威哈","masterPhone":"12345678910","password":"123456","petAge":2,"petKind":"狗","petName":"哈威","petType":"大型","userName":"hw123456"}
         * orderId : 2
         * orderType : 2
         * service : {"endTime":"17:00","id":6,"maxPrice":99,"minPrice":55,"serviceType":"驱虫","startTime":"10:00","storeId":6}
         * store : {"id":6,"password":"123456789","phone":"12345678910","qq":"1694327880","storeAddress":"湖北武汉洪山区","storeName":"终极店铺","storeType":1,"userName":"1694327888"}
         * goods : {"goodsImg":"upload\\temp\\download (2).jpg","goodsName":"卫龙辣条","goodsPrice":1999,"goodsType":"零食","id":15,"storeId":6}
         */

        private int isAccept;
        private MasterBean master;
        private int orderId;
        private int orderType;
        private ServiceBean service;
        private StoreBean store;
        private GoodsBean goods;

        public int getIsAccept() {
            return isAccept;
        }

        public void setIsAccept(int isAccept) {
            this.isAccept = isAccept;
        }

        public MasterBean getMaster() {
            return master;
        }

        public void setMaster(MasterBean master) {
            this.master = master;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public ServiceBean getService() {
            return service;
        }

        public void setService(ServiceBean service) {
            this.service = service;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class MasterBean {
            /**
             * id : 1
             * masterAddress : 中国上海
             * masterName : 威哈
             * masterPhone : 12345678910
             * password : 123456
             * petAge : 2
             * petKind : 狗
             * petName : 哈威
             * petType : 大型
             * userName : hw123456
             */

            private int id;
            private String masterAddress;
            private String masterName;
            private String masterPhone;
            private String password;
            private int petAge;
            private String petKind;
            private String petName;
            private String petType;
            private String userName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMasterAddress() {
                return masterAddress;
            }

            public void setMasterAddress(String masterAddress) {
                this.masterAddress = masterAddress;
            }

            public String getMasterName() {
                return masterName;
            }

            public void setMasterName(String masterName) {
                this.masterName = masterName;
            }

            public String getMasterPhone() {
                return masterPhone;
            }

            public void setMasterPhone(String masterPhone) {
                this.masterPhone = masterPhone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getPetAge() {
                return petAge;
            }

            public void setPetAge(int petAge) {
                this.petAge = petAge;
            }

            public String getPetKind() {
                return petKind;
            }

            public void setPetKind(String petKind) {
                this.petKind = petKind;
            }

            public String getPetName() {
                return petName;
            }

            public void setPetName(String petName) {
                this.petName = petName;
            }

            public String getPetType() {
                return petType;
            }

            public void setPetType(String petType) {
                this.petType = petType;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public static class ServiceBean {
            /**
             * endTime : 17:00
             * id : 6
             * maxPrice : 99.0
             * minPrice : 55.0
             * serviceType : 驱虫
             * startTime : 10:00
             * storeId : 6
             */

            private String endTime;
            private int id;
            private double maxPrice;
            private double minPrice;
            private String serviceType;
            private String startTime;
            private int storeId;

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getMaxPrice() {
                return maxPrice;
            }

            public void setMaxPrice(double maxPrice) {
                this.maxPrice = maxPrice;
            }

            public double getMinPrice() {
                return minPrice;
            }

            public void setMinPrice(double minPrice) {
                this.minPrice = minPrice;
            }

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }
        }

        public static class StoreBean {
            /**
             * id : 6
             * password : 123456789
             * phone : 12345678910
             * qq : 1694327880
             * storeAddress : 湖北武汉洪山区
             * storeName : 终极店铺
             * storeType : 1
             * userName : 1694327888
             */

            private int id;
            private String password;
            private String phone;
            private String qq;
            private String storeAddress;
            private String storeName;
            private int storeType;
            private String userName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
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

            public int getStoreType() {
                return storeType;
            }

            public void setStoreType(int storeType) {
                this.storeType = storeType;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public static class GoodsBean {
            /**
             * goodsImg : upload\temp\download (2).jpg
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
}
