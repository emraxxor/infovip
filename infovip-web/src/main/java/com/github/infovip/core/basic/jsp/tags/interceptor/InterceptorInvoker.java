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
package com.github.infovip.core.basic.jsp.tags.interceptor;

import com.github.infovip.core.basic.jsp.tags.DefaultModule;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.PageContext;

/**
 * Processes an interceptor if it is specified for the module.
 *
 * @author attila
 */
public final class InterceptorInvoker {

    /**
     * Current module
     */
    private DefaultModule module;

    /**
     * Interceptor's class
     */
    private Class currentClass;

    /**
     * Current interceptor
     */
    private Object interceptor;

    /**
     * Current tag
     */
    private PageContext currentContext;

    /**
     * The content only will be displayed if the content is valid
     */
    private Boolean valid;

    public InterceptorInvoker(DefaultModule module, PageContext context) {
        this.module = module;
        this.currentContext = context;
        this.valid = true;
        this.currentClass = this.module.getClass();
        try {
            String className = (String) this.module.getClass().getMethod("getInterceptor").invoke(this.module);
            if (className != null) {
                this.interceptor = Class.forName(className).newInstance();
                this.currentClass = (Class) this.interceptor.getClass();
                this.handleFields();
            }
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException ex) {
            Logger.getLogger(InterceptorInvoker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handle the default annotations
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void handleFields() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method[] methods = this.currentClass.getMethods();
        Field[] fields = this.currentClass.getDeclaredFields();

        for (Method method : methods) {
            if (method.getAnnotation(Validate.class) != null) {
                valid = (Boolean) method.invoke(this.interceptor, this.currentContext);
            }
        }

        for (Field f : fields) {
            if (f.getAnnotation(ModuleApplicationContext.class) != null) {
                f.setAccessible(true);
                f.set(this.interceptor, this.module.getApplicationContext());
            }

            if (f.getAnnotation(Module.class) != null) {
                f.setAccessible(true);
                f.set(this.interceptor, this.module);
            }

            if (f.getAnnotation(ModuleManager.class) != null) {
                f.setAccessible(true);
                f.set(this.interceptor, this.module.getManager());
            }

            if (f.getAnnotation(ModulePageContext.class) != null) {
                f.setAccessible(true);
                f.set(this.interceptor, module.getPageContext());
            }

            if (f.getAnnotation(ModuleValid.class) != null) {
                f.setAccessible(true);
                f.set(this.interceptor, valid);
            }
        }

    }

    /**
     * Invoked before the content of the module is being displayed
     *
     * @param context
     */
    public void beforeDisplay(PageContext context) {
        Method[] methods = this.currentClass.getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(BeforeDisplay.class) != null) {
                try {
                    method.invoke(this.interceptor, context);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(InterceptorInvoker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Invoked after the content of the module has been displayed
     *
     * @param context
     */
    public void afterDisplay(PageContext context) {
        Method[] methods = this.currentClass.getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(AfterDisplay.class) != null) {
                try {
                    method.invoke(this.interceptor, context);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(InterceptorInvoker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * The content only is displayed if it is valid
     *
     * @return
     */
    public Boolean isValid() {
        return valid;
    }

}
