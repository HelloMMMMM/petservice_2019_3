package com.hellom.petserviceandroid.register;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class PettypeBean extends ResponseBean {

    private List<PetTypeListBean> petTypeList;

    public List<PetTypeListBean> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetTypeListBean> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public static class PetTypeListBean {
        /**
         * id : 1
         * petTypeName : 迷你
         */

        private int id;
        private String petTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPetTypeName() {
            return petTypeName;
        }

        public void setPetTypeName(String petTypeName) {
            this.petTypeName = petTypeName;
        }
    }
}
