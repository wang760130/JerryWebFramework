package com.jerry.web.framework.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class StringActionResult implements ActionResult {
	
	private String content;
	
	public StringActionResult(String content) {
		this.content = content;
	}
	
	public void render(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(content);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
