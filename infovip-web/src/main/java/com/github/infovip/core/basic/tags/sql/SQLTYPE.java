/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.basic.tags.sql;

/**
 * Basic types for basic sql queries
 *
 * @author attila
 */
public enum SQLTYPE {
    INT("int"),
    STRING("string"),
    DOUBLE("double"),
    FLOAT("float");

    String value;

    private SQLTYPE(String v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static SQLTYPE type(String v) {
        for (SQLTYPE t : SQLTYPE.values()) {
            if (t.toString().equalsIgnoreCase(v)) {
                return t;
            }
        }
        return SQLTYPE.STRING;
    }
}
