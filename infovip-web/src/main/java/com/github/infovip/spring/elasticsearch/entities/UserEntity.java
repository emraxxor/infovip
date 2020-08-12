package com.github.infovip.spring.elasticsearch.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.MaxLength;
import com.github.infovip.core.web.validation.ValidEmail;
import com.github.infovip.entities.User;
import com.github.infovip.spring.services.UserService;

/**
 *  The introduction of the entity became justified due to a high-level search in the system. 
 *  This entity only stores public information about the users, and typical informations that are needed for 
 *  displaying.  
 *  
 *  This entity only can be managed from {@link UserService} and any modification of the data need to be corresponded to the {@link User} entity.
 *  
 * 
 *  @author Attila Barna
 */
@Document(indexName = "users")
public class UserEntity  implements ESDataElement<UserEntity>,  Serializable  {
	
	@Id
	@IgnoreField
	private String documentId;
	
	@IgnoreField
	private static final long serialVersionUID = -876168723943978388L;

	@JsonProperty("userId")
    @Field(type = FieldType.Long)
	private Long userId;
	
	@JsonProperty("userName")
    @Field(type = FieldType.Keyword)
	@MaxLength(size=200)
    private String userName;

	@JsonProperty("userMail")
    @Field(type = FieldType.Keyword)
    @MaxLength(size=200)
    @ValidEmail
    private String userMail;

	@JsonProperty("picture")
    @Field(type = FieldType.Keyword)
    @AllowEmpty
    private String picture;

    @AllowEmpty
	@JsonProperty("isActive")
    @Field(type = FieldType.Boolean)
    private Boolean isActive;

	@JsonProperty("firstName")
    @Field(type = FieldType.Auto)
    @MaxLength(size=200)
    @AllowEmpty
    private String firstName;

	@JsonProperty("lastName")
    @Field(type = FieldType.Auto)
    @MaxLength(size=200)
    @AllowEmpty
    private String lastName;

	@JsonProperty("city")
    @Field(type = FieldType.Auto)
    @MaxLength(size=200)
    @AllowEmpty
    private String city;

	@JsonProperty("country")
    @Field(type = FieldType.Auto)
    @MaxLength(size=200)
    @AllowEmpty
    private String country;

	@JsonProperty("county")
    @Field(type = FieldType.Auto)
    @MaxLength(size=200)
    @AllowEmpty
    private String county;

    
    public UserEntity() {
	}
    
	@Override
	public String getDocumentId() {
		return this.documentId;
	}

	@Override
	public UserEntity setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((county == null) ? 0 : county.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userMail == null) ? 0 : userMail.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserEntity other = (UserEntity) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (county == null) {
			if (other.county != null)
				return false;
		} else if (!county.equals(other.county))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userMail == null) {
			if (other.userMail != null)
				return false;
		} else if (!userMail.equals(other.userMail))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity [documentId=" + documentId + ", userId=" + userId + ", userName=" + userName + ", userMail="
				+ userMail + ", picture=" + picture + ", isActive=" + isActive + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", city=" + city + ", country=" + country + ", county=" + county + "]";
	}
	
	
}
