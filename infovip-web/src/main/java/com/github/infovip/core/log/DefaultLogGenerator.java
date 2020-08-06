package com.github.infovip.core.log;

import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import com.github.infovip.core.date.DefaultDateFormatter;


/**
 * 
 * @author Attila Barna
 *
 */
public final class DefaultLogGenerator<T extends BaseLogDataElement, F extends HttpServletRequest> implements LogGenerator<T, F> {

	private T element;
	
	public DefaultLogGenerator(T element, F request) {
		this.element = element;
		this.element.setIp(request.getRemoteAddr());
		this.element.getData().put("RequestURI", request.getRequestURI());
		this.element.setCreationTime(DefaultDateFormatter.current());
		this.element.setUser("UNDEFINED");
	}

	public LogGenerator<T,F> user(String user) {
		element.setUser(user);
		return this;
	}
	
	@Override
	public LogGenerator<T, F> message(String msg) {
		element.setMessage(msg);
		return this;
	}
	
	@Override
	public LogGenerator<T, F> component(String component) {
		element.setComponent(component);
		return this;
	}
	
	@Override
	public LogGenerator<T, F> put(String key, Object data) {
		element.getData().put(key, data);
		return this;
	}
	
	@Override
	public LogGenerator<T, F> update(Consumer<T> c) {
		c.accept(element);
		return this;
	}
	
	@Override
	public LogGenerator<T, F> type(String type) {
		element.setType(type);
		return this;
	}
	
	public T get() {
		return element;
	}
	
	@Override
	public BaseLogElement<T> getLogElement() {
		return new BaseLogElement<T>(element);
	}
}
