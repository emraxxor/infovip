/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web;

import com.github.infovip.core.web.js.BasicType;
import com.github.infovip.core.web.js.CSS;
import com.github.infovip.core.web.js.JavaScript;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author attila
 * @param <T>
 */
public class DefaultManager<T> {

    /**
     * Stores the scripts that are used for the page
     */
    private final List<T> data;

    public DefaultManager() {
        this.data = new LinkedList<>();
    }

    public void addElement(T elem) {
        data.add(elem);
    }

    public void removeElementBySource(final String src) {
        data.removeIf(new Predicate<T>() {
            @Override
            public boolean test(T t) {
                if (t instanceof BasicType) {
                    return ((BasicType) t).getSrc().contentEquals(src);
                }
                return false;
            }
        });
    }

    public List<T> getData() {
        return data;
    }
    
    
}
