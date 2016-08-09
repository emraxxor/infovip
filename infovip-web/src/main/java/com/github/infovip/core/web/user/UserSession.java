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

import com.github.infovip.core.Container;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author attila
 */
@Component
@Scope("session")
public class UserSession implements Serializable {

    /**
     * True if the user is authenticated, default false
     */
    private Boolean authenticated = false;

    /**
     * Reference of the Container
     */
    private Container container;

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

    public UserSession() {
    }

    /**
     * Checks if the current user is authenticated to the site
     *
     * @return the value of authenticated
     */
    public Boolean isAuthenticated() {
        return authenticated;
    }

    public Boolean getAuthenticated() {
        return this.isAuthenticated();
    }

    /**
     * Set the value of authenticated
     *
     * @param authenticated new value of authenticated
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
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
