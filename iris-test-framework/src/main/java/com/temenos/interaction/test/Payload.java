package com.temenos.interaction.test;

import java.util.List;

public interface Payload {

	List<Link> links();

	Entity entity();

	List<Entity> entities();

}
