package com.github.infovip.core.data;

import java.io.File;

public class FileInfo {

	private String name;
	
	private Long lastModified;
	
	public FileInfo(File f) {
		name = f.getName();
		lastModified = f.lastModified();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastModified
	 */
	public Long getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}
	
	
	
}
