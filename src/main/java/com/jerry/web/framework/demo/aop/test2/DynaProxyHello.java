package com.jerry.web.framework.demo.aop.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyHello implements InvocationHandler {
	
	private Object target;
	
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		this.before();
		Object result = method.invoke(target, args);
		this.after();
		return result;
	}

	private void before() {
		System.out.println("before...");
	}
	
	private void after() {
		System.out.println("after...");
	}
}
