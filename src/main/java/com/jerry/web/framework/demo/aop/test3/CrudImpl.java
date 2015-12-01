package com.jerry.web.framework.demo.aop.test3;

/**
 * 要被拦截的方法
 */
public class CrudImpl implements ICrud {

	@Override
	public void add(String user) {
		System.out.println("add user = " + user);
	}

	@Override
	public void modify(String user) {
		System.out.println("modify user = " + user);
	}

	@Override
	public void delete(String user) {
		System.out.println("delete user = " + user);
	}

}
