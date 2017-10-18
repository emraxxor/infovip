package com.github.infovip.core;

import static com.github.infovip.core.Configuration.DEBUG;

import org.apache.commons.logging.Log;;

public class ConsoleLogger implements Log {

	private String className;
	
	public ConsoleLogger() {
		this("");
	}
	
	public ConsoleLogger(String className) {
		this.className = className;
	}
	
	public ConsoleLogger(Class className) {
		this.className = className.getName();
	}
	
	public static ConsoleLogger getLogger() {
		return new ConsoleLogger();
	}
	
	public static ConsoleLogger getLogger(String className) {
		return new ConsoleLogger(className);
	}
	
	public static ConsoleLogger getLogger(Class className) {
		return new ConsoleLogger(className);
	}
	
	public void print(Object arg0, Throwable arg1) {
		if ( isDebugEnabled() ) {
			System.out.println(arg0);
			System.out.println(arg1.getMessage());
		}
	}
	
	public void print(Object arg0) {
		if ( isDebugEnabled() ) {
			System.out.println(arg0);
		}
	}
	
	@Override
	public void debug(Object arg0) {
			print(arg0);
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
			print(arg0);
			print(arg1);
	}

	@Override
	public void error(Object arg0) {
		print(arg0);
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		print(arg0);
		print(arg1);
		
	}

	@Override
	public void fatal(Object arg0) {
		print(arg0);
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		print(arg0);
		print(arg1);
		
	}

	@Override
	public void info(Object arg0) {
		print(arg0);
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		print(arg0);
		print(arg1);
	}

	@Override
	public boolean isDebugEnabled() {
		return DEBUG;
	}

	@Override
	public boolean isErrorEnabled() {
		return DEBUG;
	}

	@Override
	public boolean isFatalEnabled() {
		return DEBUG;
	}

	@Override
	public boolean isInfoEnabled() {
		return DEBUG;
	}

	@Override
	public boolean isTraceEnabled() {
		return DEBUG;
	}

	@Override
	public boolean isWarnEnabled() {
		return DEBUG;
	}

	@Override
	public void trace(Object arg0) {
		print(arg0);
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		print(arg0);
		print(arg1);
	}

	@Override
	public void warn(Object arg0) {
		print(arg0);
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		print(arg0);
		print(arg1);		
	}

}
