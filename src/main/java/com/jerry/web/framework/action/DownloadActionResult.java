package com.jerry.web.framework.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


public class DownloadActionResult implements ActionResult {
	private File file = null;
	private ServletOutputStream outputStream = null;

	public DownloadActionResult(File file, ServletOutputStream outputStream) {
		this.file = file;
		this.outputStream = outputStream;
	}

	public void render(HttpServletResponse response) throws IOException {
		if(file.exists()) {
			String filename = URLEncoder.encode(file.getName(), "utf-8");
			response.reset();
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            
            long length = file.length();
            if(length != 0) {
                InputStream is = new FileInputStream(file);
                byte[] buf = new byte[4096];
                int readLength;
                while (((readLength = is.read(buf)) != -1)) {
                	this.outputStream.write(buf, 0, readLength);
                }
                is.close();
                outputStream.flush();
                outputStream.close();
            }
            file.delete();
 		}
	}
}
