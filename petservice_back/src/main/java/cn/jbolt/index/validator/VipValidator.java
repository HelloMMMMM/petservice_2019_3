package cn.jbolt.index.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Vip;
import cn.jbolt.index.IndexController;

public class VipValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		Vip vip = c.getModel(Vip.class);
		if (vip.getPrice() == null || StrKit.isBlank(vip.getDesc())||StrKit.isBlank(vip.getName())) {
			addError("errorMsg", "请将信息填写完整！");
			return;
		}
		c.setAttr("vip", vip);
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		IndexController indexController = (IndexController) c;
		indexController.keepModel(Vip.class);
		indexController.keepPara();
		indexController.vipForm();
	}

}
