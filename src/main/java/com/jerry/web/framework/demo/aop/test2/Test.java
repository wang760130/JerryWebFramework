package com.jerry.web.framework.demo.aop.test2;

/**
 * JDK的动态代理反射实现aop
 */
public class Test {
	public static void main(String[] args) {
		IHello hello = (IHello) new DynaProxyHello().bind(new Hello());
		hello.sayHello("Kitty");
	}
}
