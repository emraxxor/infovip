package com.github.infovip.core.web.sitemap;

public class SiteMapChildCategory {

	private String urlPattern;

	private String categoryName;
	
	private String title;

	private String content;
	
	private String richText;

	public SiteMapChildCategory() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> SiteMapChildCategory create(T c) throws Exception {
		throw new Exception("Not implemented yet.");
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the richText
	 */
	public String getRichText() {
		return richText;
	}

	/**
	 * @param richText the richText to set
	 */
	public void setRichText(String richText) {
		this.richText = richText;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
