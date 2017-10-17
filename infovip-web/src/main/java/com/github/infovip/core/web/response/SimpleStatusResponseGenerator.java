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
package com.github.infovip.core.web.response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.infovip.core.web.ResponseBody;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.registration.CreateEntity;

/**
 *
 * @author attila
 */
public class SimpleStatusResponseGenerator {

    /**
     * Checks whether the CreateEntity annotation is used in the class or not.
     * If it is used then it means that a new entity-object will be created and
     * therefore the given method will be invoked.
     *
     * @param <T>
     * @param o
     * @return
     * @throws UnsupportedTypeException
     */
    public static <T> T create(T o) throws UnsupportedTypeException {
        if (!(o instanceof ResponseBody)) {
            throw new UnsupportedTypeException("It can only be used by object that implements ResponseBody.");
        }

        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CreateEntity.class) != null) {
                try {
                    method.invoke(o);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(SimpleStatusResponseGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return o;
    }
}
