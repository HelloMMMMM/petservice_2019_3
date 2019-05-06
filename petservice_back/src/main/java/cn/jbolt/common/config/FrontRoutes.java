package cn.jbolt.common.config;

import com.jfinal.config.Routes;

import cn.jbolt.index.IndexController;
import cn.jbolt.login.LoginController;
import cn.jbolt.register.RegisterController;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		// 针对一组路由配置baseViewPath
		// this.setBaseViewPath("/_view/_admin");
		// 针对一组路由配置单独的拦截器
		// this.addInterceptor(new AdminAuthInterceptor());
		// 针对后台管理系统配置路由+controller
		// this.add("/admin", AdminIndexController.class,"/index");
		setMappingSuperClass(true);
		setBaseViewPath("/");
		add("/index", IndexController.class, "/");
		add("/register", RegisterController.class, "/");
		add("/", LoginController.class, "/");
	}

}