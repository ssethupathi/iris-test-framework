package com.temenos.interaction.test.internal;

import java.util.List;

import com.temenos.interaction.test.Link;

public interface PayloadHandler<F, E> {

	String getId();

	List<Link> getLinks();

	List<EntityHandler<E>> entityHandlers();

	F getEntityHolder();
}
