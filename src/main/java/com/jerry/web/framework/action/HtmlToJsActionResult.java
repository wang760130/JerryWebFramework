package com.jerry.web.framework.action;

import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.io.VelocityWriter;
import org.apache.velocity.runtime.RuntimeInstance;
import com.bj58.wf.mvc.BeatContext;
import com.jerry.web.framework.utils.HtmlUtils;

public class HtmlToJsActionResult {
	private static final String prefix = "views";
	private static final String suffix = ".html";
	private String viewName;

	private static RuntimeInstance rtInstance = new RuntimeInstance();

	public HtmlToJsActionResult(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void render(BeatContext beat) throws Exception {
		String path = HtmlToJsActionResult.prefix + (this.viewName.startsWith("/") ? "" : "/") + this.viewName + HtmlToJsActionResult.suffix;

		Template template = Velocity.getTemplate(path);

		HttpServletResponse response = beat.getResponse();
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("text/html;charset=\"UTF-8\"");
		response.setCharacterEncoding("UTF-8");
		Context context = new VelocityContext(beat.getModel().getModel());
		StringWriter sw = new StringWriter();
		VelocityWriter vw = new VelocityWriter(sw);
		try {
			template.merge(context, vw);
			vw.flush();
		} finally {
			vw.recycle(null);
		}
		String body = "";
		String functionName = beat.getRequest().getParameter("vipcallback");
		String vipargs = beat.getRequest().getParameter("vipargs");

		if (functionName != null && !"".equals(functionName)) {
			if (vipargs != null && !"".equals(vipargs)) {
				body = HtmlUtils.htmlToJs(sw.toString(), functionName, vipargs);
			} else {
				body = HtmlUtils.htmlToJs(sw.toString(), functionName);
			}

		} else {
			body = HtmlUtils.htmlToJs(sw.toString());
		}
		response.getWriter().write(body);
	}

}
