package com.jerry.web.framework.demo.aop.test1;

public class HelloProxy implements IHello {
	private IHello hello = null;
	
	public HelloProxy(IHello hello) {
		this.hello = hello;
	}
		
	@Override
	public void sayHello(String name) {
		this.before();
		hello.sayHello(name);
		this.after();
	}
	
	private void before() {
		System.out.println("before...");
	}
	
	private void after() {
		System.out.println("after...");
	}
}
