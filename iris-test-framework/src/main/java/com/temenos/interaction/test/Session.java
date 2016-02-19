package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.InputSession;
import com.temenos.interaction.test.internal.OutputSession;
import com.temenos.interaction.test.internal.Resettable;

public interface Session extends InputSession, OutputSession, Resettable {

	Link rel();

	Link rel(String rel);

}
