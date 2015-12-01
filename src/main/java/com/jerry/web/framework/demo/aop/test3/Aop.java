package com.jerry.web.framework.demo.aop.test3;

import java.lang.reflect.Method;

/**
 * 使用java的反射 进行aop功能的模拟
 */
public class Aop {
	private static CrossCutting cut = new CrossCutting();
	private static ReflectUtils before = new ReflectUtils(cut, "before");
	private static ReflectUtils after = new ReflectUtils(cut, "after");
	
	public static void before(Object target, String methodName, Object ... params) {
		Class<?> clazz = target.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(int i = 0; i < methods.length; i++) {
			if(methods[i].getName().equals(methodName)) {
				before.invokeMethod();
				new ReflectUtils(target, methodName, params).invokeMethod();
			}
		}	
	}
	
	public static void after(Object target, String methodName, Object ... params) {
		Class<?> clazz = target.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(int i = 0; i < methods.length; i++) {
			if(methods[i].getName().equals(methodName)) {
				new ReflectUtils(target, methodName, params).invokeMethod();
				after.invokeMethod();
			}
		}
	}
	
	public static void around(Object target, String methodName, Object ... params) {
		Class<?> clazz = target.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(int i = 0; i < methods.length; i++) {
			if(methods[i].getName().equals(methodName)) {
				before.invokeMethod();
				new ReflectUtils(target, methodName, params).invokeMethod();
				after.invokeMethod();
			}
		}
	}
}
