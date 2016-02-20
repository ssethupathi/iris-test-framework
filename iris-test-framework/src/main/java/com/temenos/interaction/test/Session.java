package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.RequestSession;
import com.temenos.interaction.test.internal.ResponseSession;
import com.temenos.interaction.test.internal.Resettable;

public interface Session extends RequestSession, ResponseSession, Resettable {

	ActionableHref rel();

	ActionableHref rel(String rel);

}
