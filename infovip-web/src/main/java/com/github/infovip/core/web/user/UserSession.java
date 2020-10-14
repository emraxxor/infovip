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

/**
 *
 * @author attila
 */

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
    

    public UserSession() {
    }
    
    
    public void setRole(DefaultApplicationRole role) {
    	this.role = role;
    }
    
    public DefaultApplicationRole getRole() {
    	return this.role;
    }    
    
    public Long getUserId() {
		return userId;
	}


	public User getUser() {
		return user;
	}


	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    
    public void setUser(User user) {
		this.user = user;
	}

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

    @Override
    public String toString() {
        return "UserSession{" + "authenticated=" + authenticated + ", userName=" + userName + ", userMail=" + userMail + ", registrationDate=" + registrationDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.authenticated);
        hash = 47 * hash + Objects.hashCode(this.userName);
        hash = 47 * hash + Objects.hashCode(this.userMail);
        hash = 47 * hash + Objects.hashCode(this.registrationDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSession other = (UserSession) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userMail, other.userMail)) {
            return false;
        }
        if (!Objects.equals(this.authenticated, other.authenticated)) {
            return false;
        }
        if (!Objects.equals(this.registrationDate, other.registrationDate)) {
            return false;
        }
        return true;
    }





}
