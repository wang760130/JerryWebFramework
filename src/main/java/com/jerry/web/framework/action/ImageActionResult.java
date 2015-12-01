package com.jerry.web.framework.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ImageActionResult implements ActionResult {

	private ServletOutputStream outputStream = null;
	
	public ImageActionResult(ServletOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void render(HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		ServletOutputStream out = this.outputStream;
		out.flush();
		out.close();
	}

}
