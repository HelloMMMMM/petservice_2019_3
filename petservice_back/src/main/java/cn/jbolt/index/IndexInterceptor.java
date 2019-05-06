package cn.jbolt.index;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import cn.jbolt.common.model.Store;

public class IndexInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Store store = inv.getController().getSessionAttr("session");
		inv.getController().set("store", store);
		inv.invoke();
	}

}
