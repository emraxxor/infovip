package com.github.infovip.core.es.type;

import com.github.infovip.core.data.BaseDataElement;

/**
 * 
 * @author attila
 *
 */
public class WebshopInfromationType extends BaseDataElement {

	private Long companyId;
	
	private Long webshopId;
	
	private Long sourceId;

	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String companyName;
	
	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String webshopName;
	
	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String sourceName;
	
	private String phone1;
	
	private String phone2;
	
	private String companyAddress;
	
	private String companyMail;
	
	private String mapAddress;
	
	private String companyDescription;
	
	private String imageData;
	
	private WebshopOpeningHours openingHours;
	
	public WebshopInfromationType() {
		// TODO Auto-generated constructor stub
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getWebshopId() {
		return webshopId;
	}

	public void setWebshopId(Long webshopId) {
		this.webshopId = webshopId;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebshopName() {
		return webshopName;
	}

	public void setWebshopName(String webshopName) {
		this.webshopName = webshopName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getMapAddress() {
		return mapAddress;
	}

	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}

	public WebshopOpeningHours getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(WebshopOpeningHours openingHours) {
		this.openingHours = openingHours;
	}

	
	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((companyDescription == null) ? 0 : companyDescription.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyMail == null) ? 0 : companyMail.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((imageData == null) ? 0 : imageData.hashCode());
		result = prime * result + ((mapAddress == null) ? 0 : mapAddress.hashCode());
		result = prime * result + ((openingHours == null) ? 0 : openingHours.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
		result = prime * result + ((webshopId == null) ? 0 : webshopId.hashCode());
		result = prime * result + ((webshopName == null) ? 0 : webshopName.hashCode());
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
		WebshopInfromationType other = (WebshopInfromationType) obj;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (companyDescription == null) {
			if (other.companyDescription != null)
				return false;
		} else if (!companyDescription.equals(other.companyDescription))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyMail == null) {
			if (other.companyMail != null)
				return false;
		} else if (!companyMail.equals(other.companyMail))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (imageData == null) {
			if (other.imageData != null)
				return false;
		} else if (!imageData.equals(other.imageData))
			return false;
		if (mapAddress == null) {
			if (other.mapAddress != null)
				return false;
		} else if (!mapAddress.equals(other.mapAddress))
			return false;
		if (openingHours == null) {
			if (other.openingHours != null)
				return false;
		} else if (!openingHours.equals(other.openingHours))
			return false;
		if (phone1 == null) {
			if (other.phone1 != null)
				return false;
		} else if (!phone1.equals(other.phone1))
			return false;
		if (phone2 == null) {
			if (other.phone2 != null)
				return false;
		} else if (!phone2.equals(other.phone2))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		if (webshopId == null) {
			if (other.webshopId != null)
				return false;
		} else if (!webshopId.equals(other.webshopId))
			return false;
		if (webshopName == null) {
			if (other.webshopName != null)
				return false;
		} else if (!webshopName.equals(other.webshopName))
			return false;
		return true;
	}
}
