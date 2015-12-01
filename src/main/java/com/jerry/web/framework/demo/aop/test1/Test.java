package com.jerry.web.framework.demo.aop.test1;

/**
 * 代理模式实现aop
 */
public class Test {
	public static void main(String[] args) {
		IHello hello = new HelloProxy(new Hello());
		hello.sayHello("Kitty");
	}
}
