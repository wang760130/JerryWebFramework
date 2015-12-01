package com.jerry.web.framework.demo.aop.test4;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * cglib实现aop
 */
public class CglibAOP {
	private Enhancer enhancer = new Enhancer();
	
	/**
     * 使用Cglib进行动态代理，是创建一个子类继承至目标类，所以不需要像GDK动态代理那样提供一个接口，这样节约了类的个数。
     * JDK动态代理的特点:不能代理类，只能代理接口
     * CGLIB动态代理的特点：能代理类和接口，不能代理final类
     *  动态代理的本质：用来实现对目标对象进行增强，最终表现为类，只不过是动态创建子类，不用手工生成子类。
     */

	private MethodInterceptor interceptor;
	
	public CglibAOP(MethodInterceptor interceptor) {
		this.interceptor = interceptor;
	}
	
	public Object getProxy(Class<?> clazz) {
		//生成指定类对象的子类,也就是重写类中的业务函数
		enhancer.setSuperclass(clazz);
		//这里是回调函数，加入intercept()函数
		enhancer.setCallback(interceptor);
		//创建这个子类对象
		return enhancer.create();
	}
}
