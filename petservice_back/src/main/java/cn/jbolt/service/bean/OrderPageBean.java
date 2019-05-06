package cn.jbolt.service.bean;

import java.util.List;

public class OrderPageBean {
	private int totalPage;
	private List<OrderBean> orderBeans;
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<OrderBean> getOrderBeans() {
		return orderBeans;
	}
	public void setOrderBeans(List<OrderBean> orderBeans) {
		this.orderBeans = orderBeans;
	}
}	
