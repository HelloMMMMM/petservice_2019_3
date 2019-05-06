package com.hellom.petserviceandroid.service;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class ServiceListBean extends ResponseBean {

    private List<ServiceBean> serviceList;

    public List<ServiceBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceBean> serviceList) {
        this.serviceList = serviceList;
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
}
