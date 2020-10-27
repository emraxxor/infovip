package com.github.infovip.spring.elasticsearch.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.es.type.TimestampToString;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.MaxLength;
import com.github.infovip.core.web.validation.ValidEmail;
import com.github.infovip.entities.User;
import com.github.infovip.spring.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	
	@JsonProperty("lastSeen")
    @Field(type = FieldType.Auto)
	@AllowEmpty
	@TimestampToString
	private String lastSeen;

	public UserEntity setDocumentId(String v) {
		this.documentId = v;
		return this;
	}
	
}
