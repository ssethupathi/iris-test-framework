package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.ActionableHref;
import com.temenos.interaction.test.internal.RequestSession;
import com.temenos.interaction.test.internal.ResponseSession;

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
public interface Session extends RequestSession, ResponseSession {

	ActionableHref rel();

	ActionableHref rel(String rel);

}
