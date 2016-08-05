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
package com.github.infovip.core.web.registration;

import com.github.infovip.core.web.DefaultStatusResponseBody;
import com.github.infovip.beans.stateless.user.UserManagementLocal;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.util.BasicUtilities;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author attila
 */
public class CreateUser extends DefaultStatusResponseBody {

    /**
     * UserManagement module
     */
    UserManagementLocal userManagement;

    /**
     * The current HTTP request
     */
    private HttpServletRequest request;

    public CreateUser(HttpServletRequest request, UserManagementLocal um) {
        super();
        this.userManagement = um;
        this.request = request;
    }

    /**
     * Checks whether the input is defined or not
     *
     * @param data
     * @return
     */
    private boolean checkEmptyInputs(String[] data) {
        for (String o : data) {
            if (request.getParameter(o) == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Stores the user into the database
     */
    @CreateEntity
    public void create() {
        String[] inputs = new String[]{"uname", "upassword", "umail"};
        if (checkEmptyInputs(inputs)) {
            if (userManagement.findUserByEmail(request.getParameter(inputs[2])) != null) {
                statusMessage = String.format("Email : %s has been already used!", request.getParameter(inputs[2]));
                status = "mail";
                return;
            }

            if (userManagement.findUserByName(request.getParameter(inputs[0])) != null) {
                statusMessage = String.format("Username : %s is not available!", request.getParameter(inputs[0]));
                status = "user";
                return;
            }

            User u = new User(0L,
                    request.getParameter("uname"),
                    BasicUtilities.getMD5(request.getParameter("upassword")),
                    request.getParameter("umail")
            );

            LogRegistration lg = new LogRegistration(0L, request.getRemoteAddr(), new Date(System.currentTimeMillis()));
            u.setLogRegistration(lg);
            lg.setUid(u);

            User res = userManagement.createNewUser(u);
            if (res.getUserId() != null) {
                status = "ok";
                statusMessage = String.format("User %s %d has been successfully created.", res.getUserName(), res.getUserId());
            }
        }
    }

}
