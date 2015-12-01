package com.jerry.web.framework.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class RedirectActionResult implements ActionResult {
	private String url; 
	
	public RedirectActionResult(String url) {
		this.url = url;
	}
	
	public void render(HttpServletResponse response) throws IOException {
		response.sendRedirect(url);
	}

}
