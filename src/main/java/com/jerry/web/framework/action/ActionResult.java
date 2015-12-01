package com.jerry.web.framework.action;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;


public interface ActionResult {
	
	public void render(HttpServletResponse response) throws IOException;
}
