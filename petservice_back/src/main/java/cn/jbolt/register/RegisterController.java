package cn.jbolt.register;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import cn.jbolt.service.StoreService;

@Clear
public class RegisterController extends Controller {
	
	@Inject
	private StoreService storeService;
	
	public void index() {
		render("register.html");
	}

	@Before(RegisterValidator.class)
	public void register() {
		RegisterBean registerBean = getBean(RegisterBean.class);
		storeService.addStore(registerBean);
		set("successMsg", "注册成功").render("/register.html");;
	}
}
