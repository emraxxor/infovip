/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web.js;

/**
 *
 * @author attila
 */
public class BasicType<T> {

    /**
     * Source of the element
     */
    protected String src;

    protected T type;

    public BasicType(String src,T type) {
        this.src = src;
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

}
