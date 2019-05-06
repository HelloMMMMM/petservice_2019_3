package cn.jbolt.common.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import cn.jbolt.common.model.Store;

public class CheckLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		try {
			Store store = inv.getController().getSessionAttr("session");
			if (store == null) {
				inv.getController().redirect("/");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			inv.getController().redirect("/");
			return;
		}
		inv.invoke();
	}

}
