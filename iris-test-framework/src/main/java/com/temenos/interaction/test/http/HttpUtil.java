package com.temenos.interaction.test.http;

public class HttpUtil {

	public static String removeParameter(String contentType) {
		int parameterSeparatorIndex = contentType.indexOf(";");
		if (parameterSeparatorIndex > 0) {
			return contentType.substring(0, parameterSeparatorIndex).trim();
		} else {
			return contentType;
		}
	}

	public static String extractParameter(String contentType) {
		int parameterSeparatorIndex = contentType.indexOf(";");
		if (parameterSeparatorIndex > 0) {
			return contentType.substring(parameterSeparatorIndex).trim();
		} else {
			return "";
		}
	}
}
