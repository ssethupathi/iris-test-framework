package com.temenos.interaction.test.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeader {

	private Map<String, List<String>> headers = new HashMap<>();

	public Collection<String> names() {
		return headers.keySet();
	}

	public void set(String name, String value) {
		List<String> values = headers.get(name);
		if (values == null) {
			values = new ArrayList<String>();
		}
		values.add(value);
		headers.put(name, values);
	}

	public String get(String name) {
		if (headers.containsKey(name)) {
			List<String> values = headers.get(name);
			if (values.size() > 0) {
				return values.get(0);
			}
		}
		return "";
	}

	public String toString() {
		return headers.toString();
	}
}
