package com.temenos.interaction.test.internal;

public interface Validator<T> {

	public void validate(T type, Object... forValues);
}
