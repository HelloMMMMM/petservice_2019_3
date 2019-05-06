package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Store;

public class StoreListBean extends ResponseBean{
	
	private List<Store> storeList;

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}
}
