package com.jerry.web.framework.demo.aop.test4;

/**
 * Cglib模拟aop
 */
public class Test {
	public static void main(String[] args) {
		CglibAOP aop = new CglibAOP(new AroundCutting());
		CrudImpl crud = (CrudImpl) aop.getProxy(CrudImpl.class);
		crud.modify("Ketty");
	}
}
