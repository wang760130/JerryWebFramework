package com.jerry.web.framework.demo.aop.test4;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 通过cglib进行拦截 
 */
public class AroundCutting implements MethodInterceptor{

	@Override
	public Object intercept(Object object, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("before..." + method);
		Object reuslt = proxy.invokeSuper(object, args);
		System.out.println("after..." + method);
		return reuslt;
	}

}
