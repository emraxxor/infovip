package com.github.infovip.core.data;

import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.types.FormElement;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.MaxLength;
import com.github.infovip.core.web.validation.ValidEmail;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Attila Barna 
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultUser<T> extends FormElement<T> {

	private Long userId;
	
	@MaxLength(size=200)
    private String userName;

    @MaxLength(size=200)
    private String userPassword;
    
    @IgnoreField
    private String userPasswordRepeat;
    
    @IgnoreField
    private String oldPassword;
    
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

    @MaxLength(size=200)
    @AllowEmpty
    private String country;

    @MaxLength(size=200)
    @AllowEmpty
    private String county;

    
    public DefaultUser() {
    	this.isActive = false;
	}
    
    public DefaultUser(T s) {
    	super(s);
    }
    
    public DefaultUser(T s, java.lang.reflect.Field[] fields) {
    	super(s, fields);
    }
    
}
