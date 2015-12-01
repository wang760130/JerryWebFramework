package com.jerry.web.framework.demo.aop.test4;

/**
 * 要被拦截的方法
 */
public class CrudImpl  {

	public void add(String user) {
		System.out.println("add user = " + user);
	}

	public void modify(String user) {
		System.out.println("modify user = " + user);
	}

	public void delete(String user) {
		System.out.println("delete user = " + user);
	}

}
