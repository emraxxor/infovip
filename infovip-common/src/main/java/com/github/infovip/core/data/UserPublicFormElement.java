package com.github.infovip.core.data;

import com.github.infovip.core.date.DefaultDateFormatter.DATE_FORMAT;
import com.github.infovip.core.es.type.TimestampToString;
import com.github.infovip.core.web.types.FormElement;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.MaxLength;
import com.github.infovip.core.web.validation.ValidEmail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserPublicFormElement<T> extends FormElement<T>  {

	private Long userId;
	
	@MaxLength(size=200)
    private String userName;

    @MaxLength(size=200)
    @ValidEmail
    private String userMail;

    @AllowEmpty
    private String picture;

    @AllowEmpty
    private Boolean isActive;

    @MaxLength(size=200)
    @AllowEmpty
    private String firstName;

    @MaxLength(size=200)
    @AllowEmpty
    private String lastName;

    @MaxLength(size=200)
    @AllowEmpty
    private String city;
    
    @TimestampToString(type = DATE_FORMAT.STRICT_DATE_TIME)
    private String lastSeen;

    @MaxLength(size=200)
    @AllowEmpty
    private String country;

    @MaxLength(size=200)
    @AllowEmpty
    private String county;

    
    public UserPublicFormElement() {
    	this.isActive = false;
	}
    
    public UserPublicFormElement(T s) {
    	super(s);
    	
    }
    
    public UserPublicFormElement(T s, java.lang.reflect.Field[] fields) {
    	super(s, fields);
    }

    

}
