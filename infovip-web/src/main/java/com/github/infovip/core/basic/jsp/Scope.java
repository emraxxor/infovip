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
package com.github.infovip.core.basic.jsp;

import javax.servlet.jsp.PageContext;

/**
 *
 * Simply wrapper to identify the scope by string
 *
 * @author attila
 */
public enum Scope {
    APPLICATION("application"),
    PAGE("page"),
    REQUEST("request"),
    SESSION("session");

    private String val;

    private Scope(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    /**
     * Helps to identify the scope by string.
     * If an unexpected string is used then it returns with REQUEST_SCOPE;
     * @param v
     * @return 
     */
    public static int scope(String v) {
        for (Scope s : values()) {
            if (s.toString().equals(v)) {
                switch (s) {
                    case APPLICATION:
                        return PageContext.APPLICATION_SCOPE;
                    case PAGE:
                        return PageContext.PAGE_SCOPE;
                    case REQUEST:
                        return PageContext.REQUEST_SCOPE;
                    case SESSION:
                        return PageContext.SESSION_SCOPE;
                    default:
                        return PageContext.REQUEST_SCOPE;
                }
            }
        }
        return PageContext.REQUEST_SCOPE;
    }

}
