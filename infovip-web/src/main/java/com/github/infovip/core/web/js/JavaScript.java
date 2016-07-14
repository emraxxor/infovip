/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web.js;

import com.github.infovip.core.web.exceptions.UnsupportedTypeException;

/**
 *
 * @author attila
 */
public class JavaScript extends BasicType<JavaScript.ScriptType> {

    public enum ScriptType {
        TEXT_JAVASCRIPT("text/javascript");

        private final String type;

        private ScriptType(String s) {
            type = s;
        }

        public boolean equalsType(String otherType) {
            return (otherType == null) ? false : type.equals(otherType);
        }

        /**
         *
         * @param text
         * @return
         * @throws UnsupportedTypeException
         */
        public static ScriptType value(String text) throws UnsupportedTypeException {
            for (ScriptType t : ScriptType.values()) {
                if (t.toString().equalsIgnoreCase(text)) {
                    return t;
                }
            }
            throw new UnsupportedTypeException(text);
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public JavaScript(String src, String type) throws UnsupportedTypeException {
        super(src, ScriptType.value(type));
    }

    public JavaScript(String src, ScriptType type) throws UnsupportedTypeException {
        super(src, type);
    }

    @Override
    public String toString() {
        return String.format("<script type='%s' src='%s'></script>", type, src);
    }

}
