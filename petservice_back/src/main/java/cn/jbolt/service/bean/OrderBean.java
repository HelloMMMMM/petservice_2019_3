package cn.jbolt.service.bean;

import cn.jbolt.common.model.Goods;
import cn.jbolt.common.model.Master;
import cn.jbolt.common.model.Service;
import cn.jbolt.common.model.Store;

public class OrderBean {
	private int orderId;
	private int orderType;
	private int isAccept;
	private Service service;
	private Goods goods;
	private Master master;
	private Store store;
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(int isAccept) {
		this.isAccept = isAccept;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
}
