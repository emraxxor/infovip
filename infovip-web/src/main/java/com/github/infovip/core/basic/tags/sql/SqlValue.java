/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.basic.tags.sql;

/**
 *
 * @author attila
 */
public class SqlValue {

    private SQLTYPE type;

    private String value;

    public SqlValue(SQLTYPE type, String value) {
        this.type = type;
        this.value = value;
    }

    public SQLTYPE getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(SQLTYPE type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
