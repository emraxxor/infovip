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
package com.github.infovip.core.basic.tags.elasticsearch;

/**
 * Simple wrapper for converting field types
 *
 * @author attila
 */
public enum ESEntityFieldType {
    FLOAT("float"),
    STRING("string"),
    DATE("date"),
    INTEGER("int"),
    DOUBLE("double"),
    OBJECT("object"),
    LONG("long");

    private String value;

    private ESEntityFieldType(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Gets the given enum by its string value
     *
     * @param val
     * @return
     */
    public static ESEntityFieldType value(String val) {
        for (ESEntityFieldType type : values()) {
            if (type.toString().contentEquals(val)) {
                return type;
            }
        }
        return null;
    }

}
