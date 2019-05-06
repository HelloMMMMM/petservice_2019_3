package cn.jbolt.service;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Vip;

public class VipService {
	public void addVip(Vip vip) {
		vip.save();
	}

	public void deleteVip(int id) {
		Vip.dao.deleteById(id);
	}

	public void updateVip(Vip vip) {
		Vip.dao.findById(vip.getId()).set("price", vip.getPrice()).set("desc", vip.getDesc()).update();
	}

	public Page<Vip> getVipPages(int page, String keyWord) {
		Page<Vip> pages = Vip.dao.paginate(page, 10, "select *",
				"from vip where name like concat('%',?,'%')", keyWord==null?"":keyWord);
		return pages;
	}
	
}
