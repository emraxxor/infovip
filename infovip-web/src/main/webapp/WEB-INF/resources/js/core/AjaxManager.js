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
 * AjaxManager provides to send various kinds of data via ajax.
 * @type type
 */
var AjaxManager = easejs.Class('AjaxManager', {
    /**
     * By default, it sends the given data via ajax using POST type.
     * The response should be JSON object.
     * @param {type} type
     * @param {type} url
     * @param {type} data
     * @param {type} dataType
     * @returns {undefined}
     */
    'public static send': function (url, data, type, dataType) {
        var res = {};

        if (dataType == undefined) {
            dataType = "json";
        }

        $jquery.ajax({
            type: type == undefined ? "POST" : type,
            url: url,
            data: data,
            dataType: dataType,
            cache: false,
            async: false,
            error: function (request, status, error) {
                if (request.responseText != null) {
                    console.error(request.responseText);
                }
            },
            success: function (data, textStatus, jqXHR) {
                res = data;
            }
        });
        return res;
    }

});