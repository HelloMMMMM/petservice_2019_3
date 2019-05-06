package cn.jbolt.api;

import cn.jbolt.common.model.Master;

public class MasterLoginBean extends ResponseBean{
	private Master master;

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}
}
