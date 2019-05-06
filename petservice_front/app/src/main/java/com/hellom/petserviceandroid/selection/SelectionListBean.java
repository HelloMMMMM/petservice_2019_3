package com.hellom.petserviceandroid.selection;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class SelectionListBean extends ResponseBean {

    private List<SelectionBean> selectionList;

    public List<SelectionBean> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<SelectionBean> selectionList) {
        this.selectionList = selectionList;
    }

    public static class SelectionBean {
        /**
         * id : 32
         * petImg : http://192.168.0.6:9001/upload\temp\download (14).jpg
         * petName : 花猫
         * petStar : 0
         * selectionTime : 2
         * storeId : 6
         */

        private int id;
        private String petImg;
        private String petName;
        private int petStar;
        private int selectionTime;
        private int storeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPetImg() {
            return petImg;
        }

        public void setPetImg(String petImg) {
            this.petImg = petImg;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public int getPetStar() {
            return petStar;
        }

        public void setPetStar(int petStar) {
            this.petStar = petStar;
        }

        public int getSelectionTime() {
            return selectionTime;
        }

        public void setSelectionTime(int selectionTime) {
            this.selectionTime = selectionTime;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }
}
