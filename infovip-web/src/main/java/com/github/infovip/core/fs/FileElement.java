package com.github.infovip.core.fs;

import java.io.File;

/**
 * 
 * @author Attila Barna
 * 
 * It represents a file and its basic properties.
 *
 */
public class FileElement {

	private final String name;
	
	private final long size;
	
	private final String type;
	
	private final long lastModified;
	
	public FileElement(File o,String type) {
		this.type = type;
		name = o.getName();
		size = o.length();
		lastModified = o.lastModified();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public long getLastModified() {
		return lastModified;
	}
	
	
}
