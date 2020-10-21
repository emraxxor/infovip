/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.core.web.user;

import java.util.Date;
import java.util.Objects;

import com.github.infovip.core.Container;
import com.github.infovip.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author attila
 */

@Data
@NoArgsConstructor
public class UserSession implements CurrentUserInfo<User>, WebUser {

    /**
     * True if the user is authenticated, default false
     */
    private Boolean authenticated = false;

    /**
     * Reference of the Container
     */
    private Container container;

    /**
     * Id of the current user
     */
    private Long userId;
    
    /**
     * Current user name
     */
    private String userName;

    /**
     * Current user mail address
     */
    private String userMail;

    /**
     * Current user registration date
     */
    private Date registrationDate;

    
    private User user;
    
    /**
     * Defines the user's level of authority
     */
    private DefaultApplicationRole role;
    

    @Override
    public Boolean isAuthenticated() {
    	return this.authenticated;
    }
    
    @Override
    public String userIdentifier() {
    	return userName;
    }
    
    
    @Override
    public String userMailAddress() {
    	return userMail;
    }
    
    @Override
    public String userName() {
    	return this.userName;
    }
    
    
    @Override
    public Long userId() {
    	return userId;
    }


}
