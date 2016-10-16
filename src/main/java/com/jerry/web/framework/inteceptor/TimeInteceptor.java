package com.jerry.web.framework.inteceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeInteceptor implements HandlerInterceptor{
	
	private static final Logger logger = Logger.getLogger(TimeInteceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		
		long start = System.currentTimeMillis();
		request.setAttribute("start", start);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView model) throws Exception {
		
		long start = Long.valueOf((String)request.getAttribute("start"));
		long end = System.currentTimeMillis();
		
		long execute = end - start;
		
		model.addObject("execute",execute);
		
		logger.info("[" + object + "] executeTime : " + execute + "ms.");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		
	}

}
