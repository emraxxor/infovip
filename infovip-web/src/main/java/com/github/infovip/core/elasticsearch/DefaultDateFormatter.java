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
package com.github.infovip.core.elasticsearch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author attila
 */
public class DefaultDateFormatter {

    public enum PATTERN {

        ELASTICSEARCH_DATE_PATTERN("yyyy-MM-dd'T'HH:mm:ss"),
        CHUNK_DATE("yyyy-MM-dd'T'00:00:00");

        private String value;

        private PATTERN(String v) {
            this.value = v;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String valueOf(PATTERN p) {
            for (PATTERN pattern : PATTERN.values()) {
                if (p == pattern) {
                    return p.toString();
                }
            }
            return null;
        }
    }

    public static String format(Date t) {
        return format(t, PATTERN.ELASTICSEARCH_DATE_PATTERN);
    }

    public static String format(Date t, PATTERN pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern.toString());
        return formatter.format(t);
    }
}
