package cn.jbolt.index.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Goods;
import cn.jbolt.index.IndexController;

public class GoodsValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		UploadFile uploadFile = c.getFile();
		Goods goods = c.getModel(Goods.class);
		boolean isAdd = c.getBoolean("isAdd");
		if (StrKit.isBlank(goods.getGoodsName()) || goods.getGoodsPrice() == null
				|| goods.getGoodsPrice().intValue() <= 0 || StrKit.isBlank(goods.getGoodsType())) {
			if (uploadFile != null) {
				uploadFile.getFile().delete();
			}
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		if (uploadFile != null) {
			String absolutePath = uploadFile.getFile().getAbsolutePath();
			goods.setGoodsImg(absolutePath.substring(absolutePath.indexOf("upload")).replace("\\", "/"));
			c.setAttr("goods", goods);
		} else {
			if (isAdd) {
				addError("errorMsg", "请将信息填写完整!");
			} else {
				goods.setGoodsImg(c.getPara("goodsImg"));
				c.setAttr("goods", goods);
			}
		}
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		IndexController indexController = (IndexController) c;
		indexController.keepModel(Goods.class);
		indexController.keepPara();
		indexController.goodsForm();
	}

}
