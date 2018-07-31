package com.github.infovip.core.es.type;

public class Metatag {

	private String description;
	
	private String keyword;
	
	private String title;
	
	private String ogDescription;
	
	private String ogKeyword;
	
	private String ogTitle;
	
	public Metatag() {
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOgDescription() {
		return ogDescription;
	}

	public void setOgDescription(String ogDescription) {
		this.ogDescription = ogDescription;
	}

	public String getOgKeyword() {
		return ogKeyword;
	}

	public void setOgKeyword(String ogKeyword) {
		this.ogKeyword = ogKeyword;
	}

	public String getOgTitle() {
		return ogTitle;
	}

	public void setOgTitle(String ogTitle) {
		this.ogTitle = ogTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((ogDescription == null) ? 0 : ogDescription.hashCode());
		result = prime * result + ((ogKeyword == null) ? 0 : ogKeyword.hashCode());
		result = prime * result + ((ogTitle == null) ? 0 : ogTitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metatag other = (Metatag) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (ogDescription == null) {
			if (other.ogDescription != null)
				return false;
		} else if (!ogDescription.equals(other.ogDescription))
			return false;
		if (ogKeyword == null) {
			if (other.ogKeyword != null)
				return false;
		} else if (!ogKeyword.equals(other.ogKeyword))
			return false;
		if (ogTitle == null) {
			if (other.ogTitle != null)
				return false;
		} else if (!ogTitle.equals(other.ogTitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
