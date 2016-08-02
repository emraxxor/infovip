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

import static com.github.infovip.core.elasticsearch.DefaultDateFormatter.PATTERN.STANDARD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 * DefaultDateFormatter helps to convert dates between the application and
 * Elasticsearch server.
 *
 * @author attila
 */
public class DefaultDateFormatter {

    /**
     * PATTERN which are used by the application. For more information please
     * see the properties.
     */
    public enum PATTERN {

        /**
         * Default date pattern for entities
         */
        ELASTICSEARCH_DATE_PATTERN("yyyy-MM-dd'T'HH:mm:ss", "default"),
        /**
         * If this pattern is used then the hour of the date will be modified to
         * 00:00:00
         */
        CHUNK_DATE("yyyy-MM-dd'T'00:00:00", "chunk"),
        /**
         * Pattern where the year is always written with Arabic numerals. The
         * number of the month is written with Arabic numerals but it also can
         * be written with Roman numerals, or the month's full name can be
         * written out, the first letter not being capitalized.
         * DefaultDateFormatter only supports the numerals. The day is also
         * written with Arabic numerals.
         *
         * The supported format is "yyyy-MM-dd"
         *
         */
        ISO8601_2003("yyyy-MM-dd", "iso8601"),
        /**
         * This pattern is used by the entire application.
         */
        STANDARD("yyyy-MM-dd HH:mm:ss", "standard");

        private String value;

        private String type;

        private PATTERN(String v, String t) {
            this.value = v;
            this.type = t;
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

        /**
         * Gets the type of the given pattern
         * @param type
         * @return 
         */
        public static PATTERN typeOf(String type) {
            for (PATTERN pattern : PATTERN.values()) {
                if (pattern.getType().contentEquals(type)) {
                    return pattern;
                }
            }
            return null;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
        
        

    }

    /**
     * Parses the given date by using the STANDARD format
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static DateTime parse(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(STANDARD.toString());
        return new DateTime(formatter.parse(date));
    }

    /**
     * Parses the given date using the given format
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static DateTime parse(String date, PATTERN pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern.toString());
        return new DateTime(formatter.parse(date));
    }

    /**
     * Parses the given date using the given format
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static DateTime parse(String date, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return new DateTime(formatter.parse(date));
    }

    /**
     * Default date formatter
     *
     * @param t
     * @return
     */
    public static String format(Date t) {
        return format(t, PATTERN.ELASTICSEARCH_DATE_PATTERN);
    }

    /**
     * Formats the date using the given pattern
     *
     * @param t
     * @param pattern
     * @return
     */
    public static String format(Date t, PATTERN pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern.toString());
        return formatter.format(t);
    }
}
