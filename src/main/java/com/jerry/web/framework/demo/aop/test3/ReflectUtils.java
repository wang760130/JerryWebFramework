package com.jerry.web.framework.demo.aop.test3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * java反射的一些帮助方法, 通过传入的类，拦截要执行的方法名
 * 返回后直接invoke即可
 * 具体见aop
 * 无其他意义，不是经典模式，可以去掉
 */
public class ReflectUtils {
	private Class<?> targetClass;
	private Object target;
	private Method targetMethod;
	private Object[] params;
	
	public ReflectUtils(Object target, String methodName, Object ... params) {
		this.target = target;
		this.targetClass = target.getClass();
		this.params = params;
		
		Class<?>[] clazz = new Class[params.length];
		for(int i = 0; i < params.length; i++) {
			clazz[i] = params[i].getClass();
		}
		
		try {
			this.targetMethod = targetClass.getMethod(methodName, clazz);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public void invokeMethod() {
		try {
			this.targetMethod.invoke(target, params);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
