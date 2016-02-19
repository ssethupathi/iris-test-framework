package com.temenos.interaction.test.internal;

import org.hamcrest.Matcher;

public interface Verifier<T> {

	public void verify(Matcher<T> matcher);
}
