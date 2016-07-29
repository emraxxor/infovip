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

/**
 * The location of the current web application
 * It is really recommended  to use this constant instaed of document.location
 * 
 * @type String
 */
var WEB_DIR = "/infovip-web";



// Other resources

/**
 * Creates a format method if it doesn't exists
 * 
 */
if (!String.prototype.format) {

    /**
     * Formats the message
     * @example 
     * "string {0} {1}", var1, var2
     * 
     * @returns {String.prototype@call;replace}
     */
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}

/**
 * Conflict with easejs
 * @type Object
 */
var $jquery = jQuery.noConflict();