package cn.jbolt.login;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

import cn.jbolt.common.model.Store;

@Clear
public class LoginController extends Controller {

	public void index() {
		render("/login.html");
	}

	@Before(LoginValidator.class)
	public void login() {
		Store store = getAttr("store");
		setSessionAttr("session", store);
		redirect("/index");
	}
}
