package com.github.infovip.core.web;

public class MetaTagData {

	private String title;
	
	private String description;
	
	private String metaKeyword;
	
	private String ogTitle;
	
	private String ogType;
	
	private String ogDescription;
	
	private String metaDescription;

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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the metaKeyword
	 */
	public String getMetaKeyword() {
		return metaKeyword;
	}

	/**
	 * @param metaKeyword the metaKeyword to set
	 */
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}

	/**
	 * @return the ogTitle
	 */
	public String getOgTitle() {
		return ogTitle;
	}

	/**
	 * @param ogTitle the ogTitle to set
	 */
	public void setOgTitle(String ogTitle) {
		this.ogTitle = ogTitle;
	}

	/**
	 * @return the ogType
	 */
	public String getOgType() {
		return ogType;
	}

	/**
	 * @param ogType the ogType to set
	 */
	public void setOgType(String ogType) {
		this.ogType = ogType;
	}

	/**
	 * @return the ogDescription
	 */
	public String getOgDescription() {
		return ogDescription;
	}

	/**
	 * @param ogDescription the ogDescription to set
	 */
	public void setOgDescription(String ogDescription) {
		this.ogDescription = ogDescription;
	}
	
	

	/**
	 * @return the metaDescription
	 */
	public String getMetaDescription() {
		return metaDescription;
	}

	/**
	 * @param metaDescription the metaDescription to set
	 */
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((metaDescription == null) ? 0 : metaDescription.hashCode());
		result = prime * result + ((metaKeyword == null) ? 0 : metaKeyword.hashCode());
		result = prime * result + ((ogDescription == null) ? 0 : ogDescription.hashCode());
		result = prime * result + ((ogTitle == null) ? 0 : ogTitle.hashCode());
		result = prime * result + ((ogType == null) ? 0 : ogType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetaTagData other = (MetaTagData) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (metaDescription == null) {
			if (other.metaDescription != null)
				return false;
		} else if (!metaDescription.equals(other.metaDescription))
			return false;
		if (metaKeyword == null) {
			if (other.metaKeyword != null)
				return false;
		} else if (!metaKeyword.equals(other.metaKeyword))
			return false;
		if (ogDescription == null) {
			if (other.ogDescription != null)
				return false;
		} else if (!ogDescription.equals(other.ogDescription))
			return false;
		if (ogTitle == null) {
			if (other.ogTitle != null)
				return false;
		} else if (!ogTitle.equals(other.ogTitle))
			return false;
		if (ogType == null) {
			if (other.ogType != null)
				return false;
		} else if (!ogType.equals(other.ogType))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
}
