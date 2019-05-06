package cn.jbolt.api;

import java.util.List;

import cn.jbolt.service.bean.OrderBean;

public class OrderListBean extends ResponseBean{
	private List<OrderBean> orderBeans;

	public List<OrderBean> getOrderBeans() {
		return orderBeans;
	}

	public void setOrderBeans(List<OrderBean> orderBeans) {
		this.orderBeans = orderBeans;
	}
	
}
