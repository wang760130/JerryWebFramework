package com.jerry.web.framework.demo.aop.test3;

/**
 * Aspect方式（JDK实现）
 * JDK只能基于接口动态代理
 */
public class Test {
	public static void main(String[] args) {
		ICrud crud = new CrudImpl();
		Aop.around(crud, "add", "Ketty");
	}
}
