package com.github.infovip.core.data;

import java.io.File;

import lombok.Data;

@Data
public class FileInfo {

	private String name;
	
	private Long lastModified;
	
	public FileInfo(File f) {
		name = f.getName();
		lastModified = f.lastModified();
	}

	
}
