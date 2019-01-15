package com.github.infovip.core.web.sitemap;

import java.util.List;

public class SiteMapIndexData {

	private List<SiteMapCategory> categories;
	
	public SiteMapIndexData() {}

	public static <T> SiteMapIndexData create(List<T> data) throws Exception {
		throw new Exception("Not implemented yet.");
	}
	
	public void setCategories(List<SiteMapCategory> categories) {
		this.categories = categories;
	}
	
	public List<SiteMapCategory> getCategories() {
		return categories;
	}
	
	public SiteMapCategory getCategoryWhereUrlPattern(String pattern) {
		return categories.stream().filter(o->o.getUrlPattern().equals(pattern)).findFirst().orElse(null);
	}
}
