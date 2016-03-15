package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.Url;

/**
 * This defines a session which is the handle...
 * <p>
 *
 * <h1>Contents</h1>
 * <h2>1. Getting entities and verify they are not empty</h2>
 * Let's do some simple GET
 * <pre>
 * {@code
 * //Create a new session
 * Session session = SessionWrapper.newSession();
 * 
 * //Set the headers, build the url and execute
 * session.header("Content-Type", "application/atom+xml")
 * 		  .url()
 * 		  .baseuri("http://somehost/service/MyService")
 * 		  .path("Books()")
 * 		  .get();
 * //Verify
 * assertEquals(200, session.result().code());
 * assertFalse(session.entities().isEmpty());
 *}
 * </pre>
 * 
 * <h2 id="1">2. Getting entities with query options</a></h2>
 * 
 * <pre class="code">
 * <code class="java">
 * //Create a new session
 * Session session = SessionWrapper.newSession();
 * 
 * //Set the headers, build the url and execute
 * session.header("Content-Type", "application/atom+xml")
 * 		  .url()
 * 		  .baseuri("http://somehost/service/MyService")
 * 		  .path("Books()")
 * 		  .queryParam("$filter=startswith(Name,'Patterns')")
 * 		  .get();
 * //Verify
 * assertEquals(200, session.result().code());
 * </code>
 * </pre>
 * <p>
 * 
 * 
 * @author ssethupathi
 *
 */
public interface Session {

	/**
	 * Sets the header name-value pair to the session.
	 * 
	 * @param name
	 *            of the header property
	 * @param value
	 *            to the header property
	 * @return this session
	 */
	Session header(String name, String value);

	Session header(String name, String... values);

	Session registerHandler(String mediaType,
			Class<? extends PayloadHandler> handler);

	Session set(String propertyName, String propertyValue);

	Url url();

	Url url(String rel);

	Session reuse();

	Session use(EntityWrapper entity);
	
	Session clear();

	Result result();

	String header(String name);

	Links links();

	Entity entity();

	Entities entities();
	
	Session basicAuthUser(String username);
	
	Session basicAuthPassword(String password);

}
