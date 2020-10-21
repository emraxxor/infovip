package com.github.infovip.core.config.deprecated;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class TemporaryConfig {
	
	private Smtp smtp;

	private String websiteAddress;
	
	private List<String> staticSiteAddress;
	
	private Facebook facebook;
	
	private GoogleAuth google;
	
	private BarionConfiguration barion;
	
	private NetworkFileSystem nfs;
	
	public TemporaryConfig() {
		this.staticSiteAddress = new ArrayList<>();
	}
	
}
