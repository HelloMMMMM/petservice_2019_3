package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Vip;

public class VipListBean extends ResponseBean{
	private List<Vip> vips;

	public List<Vip> getVips() {
		return vips;
	}

	public void setVips(List<Vip> vips) {
		this.vips = vips;
	}

	
}
