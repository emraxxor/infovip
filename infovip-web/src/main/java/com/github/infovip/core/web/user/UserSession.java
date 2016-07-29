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
import org.springframework.stereotype.Component;

/**
 *
 * @author attila
 */
public class UserSession implements Serializable {

    /**
     * True if the user is authenticated, default false
     */
    private Boolean authenticated = false;
    
    private Container container;

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
}
