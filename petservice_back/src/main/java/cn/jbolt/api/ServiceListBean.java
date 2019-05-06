package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Service;

public class ServiceListBean extends ResponseBean{
	private List<Service> serviceList;

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}
	
}
