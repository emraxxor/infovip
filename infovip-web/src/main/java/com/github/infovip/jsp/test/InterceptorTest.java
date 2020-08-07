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
package com.github.infovip.jsp.test;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.SESSION.USER_SESSION;
import com.github.infovip.core.basic.jsp.tags.interceptor.AfterDisplay;
import com.github.infovip.core.basic.jsp.tags.interceptor.BeforeDisplay;
import com.github.infovip.core.basic.jsp.tags.interceptor.ModuleApplicationContext;
import com.github.infovip.core.basic.jsp.tags.interceptor.ModulePageContext;
import com.github.infovip.core.basic.jsp.tags.interceptor.ModuleValid;
import com.github.infovip.core.basic.jsp.tags.interceptor.Validate;
import com.github.infovip.core.web.user.UserSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author attila
 */
public class InterceptorTest {

    @ModuleApplicationContext
    private ApplicationContext appContext;

    @ModulePageContext
    private PageContext modulePageContext;

    @ModuleValid
    private Boolean valid;

    @BeforeDisplay
    public void before(PageContext context) {
        try {
            JspWriter out = context.getOut();
            if (valid) {
                out.println(String.format("Hello, %s", ((UserSession) context.getSession().getAttribute(USER_SESSION.toString())).getUserName()));
            } else {
                out.println("You must log in to access this content.");
            }
        } catch (IOException ex) {
            Logger.getLogger(InterceptorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Validate
    public boolean valid(PageContext context) {
        return ((UserSession) context.getSession().getAttribute(USER_SESSION.toString())).isAuthenticated();
    }

    @AfterDisplay
    public void after(PageContext context) {
        try {
            context.getOut().println("After....");
        } catch (IOException ex) {
            Logger.getLogger(InterceptorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
