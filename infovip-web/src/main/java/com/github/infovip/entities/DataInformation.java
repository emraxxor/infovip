package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the data_information database table.
 * 
 */
@Entity
@Table(name="data_information")
@NamedQuery(name="DataInformation.findAll", query="SELECT d FROM DataInformation d")
public class DataInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="model_id")
	private Long modelId;

	@Column(name="company_address")
	private String companyAddress;

	@Column(name="company_description")
	private String companyDescription;

	@Column(name="company_mail")
	private String companyMail;

	@Column(name="company_name")
	private String companyName;

	@Column(name="image_data")
	private String imageData;

	@Column(name="map_address")
	private String mapAddress;

	@Column(name="opening_hours")
	private String openingHours;

	private String phone1;

	private String phone2;

	@Column(name="source_name")
	private String sourceName;

	@Column(name="unique_key")
	private String uniqueKey;

	@Column(name="webshop_name")
	private String webshopName;

	public DataInformation() {
	}
	
	

	public DataInformation(Long modelId, String companyAddress, String companyDescription, String companyMail,
			String companyName, String imageData, String mapAddress, String openingHours, String phone1, String phone2,
			String sourceName, String uniqueKey, String webshopName) {
		super();
		this.modelId = modelId;
		this.companyAddress = companyAddress;
		this.companyDescription = companyDescription;
		this.companyMail = companyMail;
		this.companyName = companyName;
		this.imageData = imageData;
		this.mapAddress = mapAddress;
		this.openingHours = openingHours;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.sourceName = sourceName;
		this.uniqueKey = uniqueKey;
		this.webshopName = webshopName;
	}



	public Long getModelId() {
		return this.modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyDescription() {
		return this.companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyMail() {
		return this.companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getImageData() {
		return this.imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getMapAddress() {
		return this.mapAddress;
	}

	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}

	public String getOpeningHours() {
		return this.openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getWebshopName() {
		return this.webshopName;
	}

	public void setWebshopName(String webshopName) {
		this.webshopName = webshopName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((companyDescription == null) ? 0 : companyDescription.hashCode());
		result = prime * result + ((companyMail == null) ? 0 : companyMail.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((imageData == null) ? 0 : imageData.hashCode());
		result = prime * result + ((mapAddress == null) ? 0 : mapAddress.hashCode());
		result = prime * result + ((modelId == null) ? 0 : modelId.hashCode());
		result = prime * result + ((openingHours == null) ? 0 : openingHours.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
		result = prime * result + ((uniqueKey == null) ? 0 : uniqueKey.hashCode());
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
		DataInformation other = (DataInformation) obj;
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
		if (modelId == null) {
			if (other.modelId != null)
				return false;
		} else if (!modelId.equals(other.modelId))
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
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		if (uniqueKey == null) {
			if (other.uniqueKey != null)
				return false;
		} else if (!uniqueKey.equals(other.uniqueKey))
			return false;
		if (webshopName == null) {
			if (other.webshopName != null)
				return false;
		} else if (!webshopName.equals(other.webshopName))
			return false;
		return true;
	}
	
	

}