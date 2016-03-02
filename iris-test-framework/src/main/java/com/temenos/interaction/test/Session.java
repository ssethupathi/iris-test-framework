package com.temenos.interaction.test;

import java.util.List;

import com.temenos.interaction.test.http.HttpHeader;
import com.temenos.interaction.test.internal.EntityHandler;
import com.temenos.interaction.test.internal.Url;

/**
 * The Session enables executing ...
 * <p>
 *
 * <h1>Contents</h1>
 *
 * <h3 id="1">1. Getting all entities and verify they are not empty</a></h3>
 *
 * <pre class="code">
 * <code class="java">
 * 
 * //Prepare the session
 * Session session = SessionWrapper.newSession();
 * session.entityName("Customer");
 * session.queryParam("filter=Id eq '123456'");
 * 
 * //Execute
 * session.rel().get();
 * 
 * 
 * //Verify
 * assertEquals(200, session.result().code());
 * etc.,
 * </code>
 * </pre>
 * <p>
 * TODO complete doc
 * 
 * @author ssethupathi
 *
 */
public interface Session {

	Url url();

	Url url(String rel);

	void registerEntityHandler(String mediaType,
			Class<? extends EntityHandler> handler);

	Session header(String name, String value);

	Session header(String name, String... values);

	Session set(String propertyName, String propertyValue);
	
	Session use();
	
	Session clear();

	Result result();
	
	String header(String name);
	
	Links links();

	Payload payload();

//	Entity entity();
//
//	List<Entity> entities();

}
