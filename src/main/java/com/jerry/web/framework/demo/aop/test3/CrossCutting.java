package com.jerry.web.framework.demo.aop.test3;

/**
 * 拦截指定方法后要执行的方法
 */
public class CrossCutting {
	
	public void before() {
		System.out.println("before...");
	}
	
	public void after() {
		System.out.println("after...");
	}
}
