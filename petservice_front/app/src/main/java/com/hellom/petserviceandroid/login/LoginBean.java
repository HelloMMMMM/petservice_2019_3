package com.hellom.petserviceandroid.login;

import com.hellom.petserviceandroid.base.ResponseBean;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class LoginBean extends ResponseBean {

    /**
     * master : {"id":3,"masterAddress":"中国台湾","masterName":"小主人","masterPhone":"12345678910","petAge":3,"petImg":"http://192.168.0.6:9001/upload\\temp\\IMG_20190322_091553_BURST43.jpg","petKind":"狗","petName":"节能狗","petType":"中型"}
     */

    private MasterBean master;

    public MasterBean getMaster() {
        return master;
    }

    public void setMaster(MasterBean master) {
        this.master = master;
    }

    public static class MasterBean {
        /**
         * id : 3
         * masterAddress : 中国台湾
         * masterName : 小主人
         * masterPhone : 12345678910
         * petAge : 3
         * petImg : http://192.168.0.6:9001/upload\temp\IMG_20190322_091553_BURST43.jpg
         * petKind : 狗
         * petName : 节能狗
         * petType : 中型
         */

        private int id;
        private String masterAddress;
        private String masterName;
        private String masterPhone;
        private int petAge;
        private String petImg;
        private String petKind;
        private String petName;
        private String petType;
        private int vipId;

        public int getVipId() {
            return vipId;
        }

        public void setVipId(int vipId) {
            this.vipId = vipId;
        }

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

        public int getPetAge() {
            return petAge;
        }

        public void setPetAge(int petAge) {
            this.petAge = petAge;
        }

        public String getPetImg() {
            return petImg;
        }

        public void setPetImg(String petImg) {
            this.petImg = petImg;
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
    }
}
