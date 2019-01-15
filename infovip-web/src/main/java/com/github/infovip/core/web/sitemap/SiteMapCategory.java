package com.github.infovip.core.web.sitemap;

import java.util.List;

public class SiteMapCategory {

	private String urlPattern;
	
	private String categoryName;
	
	private List<String> marker;
	
	private String image;
	
	private String type;
	
	private List<SiteMapChildCategory> child;

	public static class TYPE {
		public static String ROOT = "root";
		public static String CHILD = "child";
	}
	
	/**
	 * @return the urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}




	/**
	 * @param urlPattern the urlPattern to set
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}




	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}




	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




	/**
	 * @return the marker
	 */
	public List<String> getMarker() {
		return marker;
	}




	/**
	 * @param marker the marker to set
	 */
	public void setMarker(List<String> marker) {
		this.marker = marker;
	}


	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the child
	 */
	public List<SiteMapChildCategory> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(List<SiteMapChildCategory> child) {
		this.child = child;
	}
	
	
	
}
