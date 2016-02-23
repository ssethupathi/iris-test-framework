package com.temenos.interaction.test.http;

public class HttpClientFactory<T> {

	// static <H extends FruitHandler<F>, F extends Fruit>
	// FruitHandlerFactory<H, F> createFactory(
	// final Class<H> handlerClass, final Class<F> fruitClass) {
	// return new FruitHandlerFactory<H, F>(handlerClass, fruitClass);
	// }

	public static <E extends HttpClient<T>, T> HttpClientFactory<T> createFactory(
			final Class<E> handlerType, final Class<T> dataType) {
		return null;
	}
}
