package cn.jbolt.login;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Store;
import cn.jbolt.service.StoreService;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		LoginBean loginBean = c.getBean(LoginBean.class);
		if (StrKit.isBlank(loginBean.getUserName()) || StrKit.isBlank(loginBean.getPassword())) {
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		StoreService storeService = new StoreService();
		if (!storeService.isExists(loginBean.getUserName())) {
			addError("errorMsg", "该用户名不存在,请注册!");
			return;
		}
		Store store = storeService.login(loginBean.getUserName(), loginBean.getPassword());
		if (store != null) {
			c.set("store", store);
		}else {
			addError("errorMsg", "密码错误，请重试！");
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.keepBean(LoginBean.class);
		c.render("/login.html");
	}

}
