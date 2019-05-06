package cn.jbolt.service;

import java.util.List;

import cn.jbolt.common.model.Goodstype;

public class GoodsTypeService {
	public List<Goodstype> getGoodsTypes(){
		return Goodstype.dao.findAll();
	}
}
