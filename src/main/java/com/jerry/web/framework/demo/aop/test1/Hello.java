package com.jerry.web.framework.demo.aop.test1;

public class Hello implements IHello {

	@Override
	public void sayHello(String name) {
		System.out.println(name + " say hello!");
	}

}
