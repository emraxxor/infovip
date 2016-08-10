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

/* global ScriptLoader, easejs, Runnable */


/**
 * Although the documentation suggests to use the AMD API to load the dependencies
 * of dojo, I was forced to use a workaround because the AMD API unable to recognize
 * that the Jquery has been loaded or not (actually window.JQuery is undefined).
 * I tried to use AMD to load jquery and jquery-ui, but always an exception 
 * was thrown. Actually, this is the real reason why I use my own dojo loader 
 * instaed of AMD.
 * 
 * For more information see the following URL:
 * https://dojotoolkit.org/reference-guide/1.7/loader/amd.html
 * @type type
 */
var DojoLoader = easejs.Class('DojoLoader', {
    __construct: function (scriptLoader) {
        if (easejs.Class.isA(Runnable, scriptLoader)) {
            scriptLoader.run();
        }
    }
});

