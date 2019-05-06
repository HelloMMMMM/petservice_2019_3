package cn.jbolt.service;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Store;
import cn.jbolt.register.RegisterBean;

public class StoreService {
	public boolean isExists(String userName) {
		List<Store> stores = Store.dao.find("select userName from store where userName=?", userName);
		return stores != null && stores.size() > 0;
	}

	public Store login(String userName, String password) {
		List<Store> stores = Store.dao.find("select * from store where userName=? and password=?", userName, password);
		return (stores != null && stores.size() > 0) ? stores.get(0) : null;
	}

	public void addStore(RegisterBean registerBean) {
		new Store().setUserName(registerBean.getUserName()).setPassword(registerBean.getPassword())
				.setStoreName(registerBean.getStoreName()).setStoreAddress(registerBean.getStoreAddress())
				.setPhone(registerBean.getPhone()).setQq(registerBean.getQq()).setStoreType(1).save();
	}

	public Page<Store> getStorePage(String keyWord, int page) {
		Page<Store> pages = null;
		if (StrKit.isBlank(keyWord)) {
			pages = Store.dao.paginate(page, 10, "select *", "from store where storeType=1  order by storeStar desc");
		} else {
			pages = Store.dao.paginate(page, 10, "select *",
					"from store where storeType=1 and (storeName like concat('%',?,'%') or storeAddress like concat('%',?,'%')) order by storeStar desc",
					keyWord, keyWord);
		}
		return pages;
	}

	public Store findStoreById(int id) {
		return Store.dao.findById(id);
	}

	public void starStore(int id) {
		Store store = findStoreById(id);
		int currentStar = store.getStoreStar();
		store.setStoreStar(currentStar + 1).update();
	}
}
