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
package com.github.infovip.core.basic.jsp.tags;

import static com.github.infovip.core.Configuration.sessionValue;
import static com.github.infovip.core.Configuration.SESSION.USER_SESSION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.infovip.core.web.user.UserSession;

/**
 *
 * @author attila
 */
public class WebModule extends DefaultModule {

    private Boolean authenticated;

    /**
     * Creates new instance of tag handler
     */
    public WebModule() {
        super();
        this.authenticated = false;
    }

    @Override
    public int doStartTag() throws JspException {
        context = RequestContextUtils.findWebApplicationContext((HttpServletRequest) pageContext.getRequest());
        if (authenticated == true && ((UserSession) context.getBean(sessionValue(USER_SESSION))).isAuthenticated().equals(false)) {
            return SKIP_PAGE;
        }
        return super.doStartTag();
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setAuthenticated(String authenticated) {
        this.authenticated = Boolean.valueOf(authenticated);
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

}
