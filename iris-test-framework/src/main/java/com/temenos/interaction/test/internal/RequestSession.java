package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.ActionableHref;
import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public interface RequestSession {

	void entityId(String id);
	
	void entityName(String name);
	
	void header(String name, String value);

	void queryParam(String value);

	void entity(Entity entity);

	void setProperty(String name, String value);
	
}
