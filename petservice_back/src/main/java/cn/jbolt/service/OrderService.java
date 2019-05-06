package cn.jbolt.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Orderform;
import cn.jbolt.service.bean.OrderBean;
import cn.jbolt.service.bean.OrderPageBean;

public class OrderService {
	public OrderPageBean getOrderPage(int page, int masterId, int storeId) {
		OrderPageBean orderPageBean = new OrderPageBean();
		Page<Orderform> pages = null;
		if (storeId!=-1) {
			//后台，查看商店订单或根据下单人查看订单
			if (masterId != -1) {
				pages = Orderform.dao.paginate(page, 10, "select * ", "from orderform where storeId = ? and masterId = ? order by id desc",
						storeId, masterId);
			} else {
				pages = Orderform.dao.paginate(page, 10, "select * ", "from orderform where storeId = ? order by id desc", storeId);
			}
		}else {
			//API,查看个人订单
			pages = Orderform.dao.paginate(page, 10, "select * ", "from orderform where masterId = ? order by id desc", masterId);
		}
		UserService userService = new UserService();
		StoreService storeService=new StoreService();
		orderPageBean.setTotalPage(pages.getTotalPage());
		List<OrderBean> orderBeans = new ArrayList<>();
		List<Orderform> orders = pages.getList();
		if (orders != null) {
			for (Orderform order : orders) {
				OrderBean orderBean = new OrderBean();
				orderBean.setOrderId(order.getId());
				orderBean.setOrderType(order.getOrderType());
				// 1:商品订单，2：服务订单
				if (orderBean.getOrderType() == 1) {
					GoodsService goodsService = new GoodsService();
					orderBean.setGoods(goodsService.getGoods(order.getGoodsId()));
				} else if (orderBean.getOrderType() == 2) {
					ServiceService serviceService = new ServiceService();
					orderBean.setService(serviceService.getService(order.getServiceId()));
					// 1:未审核；2：已审核；
					orderBean.setIsAccept(order.getIsAccept());
				}
				orderBean.setStore(storeService.findStoreById(order.getStoreId()));
				orderBean.setMaster(userService.findMasterById(order.getMasterId()));
				orderBeans.add(orderBean);
			}
		}
		orderPageBean.setOrderBeans(orderBeans);
		return orderPageBean;
	}

	public void passOrder(int orderId) {
		Orderform.dao.findById(orderId).set("isAccept", 2).update();
	}

	public void createOrder(int orderType, int masterId, int storeId, int serviceId, int goodsId) {
		Orderform orderform = new Orderform().setOrderType(orderType).setMasterId(masterId).setStoreId(storeId);
		if (orderType == 1) {
			orderform.setGoodsId(goodsId);
		} else if (orderType == 2) {
			orderform.setServiceId(serviceId).setIsAccept(1);
		}
		orderform.save();
	}
}
