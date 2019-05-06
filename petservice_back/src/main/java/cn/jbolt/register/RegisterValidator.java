package cn.jbolt.register;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

import cn.jbolt.service.StoreService;

public class RegisterValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		RegisterBean registerBean = c.getBean(RegisterBean.class);
		if (StrKit.isBlank(registerBean.getUserName()) || StrKit.isBlank(registerBean.getPassword())
				|| StrKit.isBlank(registerBean.getConfirmPassword()) || StrKit.isBlank(registerBean.getStoreName())
				|| StrKit.isBlank(registerBean.getStoreAddress()) || StrKit.isBlank(registerBean.getPhone())
				|| StrKit.isBlank(registerBean.getQq())) {
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		if (!registerBean.getPassword().equals(registerBean.getConfirmPassword())) {
			addError("errorMsg", "两次输入密码不一致!");
			return;
		}
		if (new StoreService().isExists(registerBean.getUserName())) {
			addError("errorMsg", "该用户名已被注册!");
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.keepBean(RegisterBean.class);
		c.render("register.html");
	}

}
