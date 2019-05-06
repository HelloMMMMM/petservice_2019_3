package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Goods;

public class GoodsListBean extends ResponseBean{
	private List<Goods> goodsList;

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
