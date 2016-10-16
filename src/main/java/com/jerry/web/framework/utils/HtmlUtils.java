package com.jerry.web.framework.utils;

import org.apache.commons.lang.StringEscapeUtils;

public class HtmlUtils {
	public static String htmlToJs(String html) {
		return htmlToJs(html, "document.write");
	}

	public static String htmlToJs(String html, String functionName) {
		html = html.replace("\\", "\\\\");
		html = html.replace("/", "\\/");
		html = html.replace("\"", "\\\"");
		html = html.replace("\n", "\\n");
		html = html.replace("\r", "\\r");
		return StringEscapeUtils.escapeHtml(functionName) + "(\"" + html + "\");";
	}

	public static String htmlToJs(String html, String functionName, String args) {
		html = html.replace("\\", "\\\\");
		html = html.replace("/", "\\/");
		html = html.replace("\"", "\\\"");
		html = html.replace("\n", "\\n");
		html = html.replace("\r", "\\r");
		return StringEscapeUtils.escapeHtml(functionName) + "(\"" + html + "\",\"" + StringEscapeUtils.escapeHtml(args) + "\");";
	}
}
