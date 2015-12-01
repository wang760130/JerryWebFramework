package com.jerry.web.framework.action;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import com.jerry.web.framework.utils.DESCipher;
import com.jerry.web.framework.utils.GZipUtils;

public class GZipCipherActionResult implements ActionResult{
	private String str = null;
	
	public GZipCipherActionResult(String str) {
		this.str = str;
	}
	
	@Override
	public void render(HttpServletResponse response)  {
		response.setContentType ("application/octet-stream");
		try {
			byte[] result = GZipUtils.compress(str.getBytes("UTF-8"));
			result = DESCipher.encrypt(result, ""); 
			response.getOutputStream().write(result);
			response.getOutputStream().flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
