package cn.jbolt.service;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Goods;

public class GoodsService {
	public void addGoods(Goods goods) {
		goods.save();
	}

	public Page<Goods> getGoodsPage(int page, String keyWord, int storeId) {
		Page<Goods> pages = null;
		if (keyWord != null) {
			pages = Goods.dao.paginate(page, 10, "select *",
					"from goods where storeId = ? and goodsName like concat('%',?,'%')", storeId, keyWord);
		} else {
			pages = Goods.dao.paginate(page, 10, "select *", "from goods where storeId = ?", storeId);
		}
		return pages;
	}

	public void deleteGoods(int id) {
		Goods.dao.deleteById(id);
	}

	public void updateGoods(Goods goods) {
		Goods.dao.findById(goods.getId()).set("goodsName", goods.getGoodsName())
				.set("goodsPrice", goods.getGoodsPrice()).set("goodsImg", goods.getGoodsImg())
				.set("goodsType", goods.getGoodsType()).update();
	}
	
	public Goods getGoods(int goodsId){
		return Goods.dao.findById(goodsId);
	}
}
