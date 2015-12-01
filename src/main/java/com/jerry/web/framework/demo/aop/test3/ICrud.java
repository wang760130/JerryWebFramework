package com.jerry.web.framework.demo.aop.test3;

/**
 * 要被拦截的方法
 */
public interface ICrud {
	public void add(String user);
	
	public void modify(String user);
	
	public void delete(String user);
}
