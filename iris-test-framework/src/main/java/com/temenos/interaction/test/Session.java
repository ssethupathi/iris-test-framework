package com.temenos.interaction.test;

import java.util.List;

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

	Session header(String name, String value);

	Session header(String name, String... values);
	
	void registerHandler(String mediaType,
			Class<? extends PayloadHandler> handler);

	Session set(String propertyName, String propertyValue);
	
	Url url();

	Url url(String rel);
	
	Session reuse();
	
	Session clear();

	Result result();
	
	String header(String name);
	
	List<Link> links();
	
	Links link();

	Entity entity();

	List<? extends Entity> entities();

}
