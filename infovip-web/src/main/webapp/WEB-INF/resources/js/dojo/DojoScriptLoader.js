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

/* global easejs, Runnable, ScriptLoader, $jquery */

/**
 * A simple script loader, it waits until the page is loaded then loads the
 * dojo and its dependencies.
 * @type type
 */
var DojoScriptLoader = easejs.Class('DojoScriptLoader').implement(Runnable).extend({
    'public run': function () {
        $jquery(document).ready(function () {
            ScriptLoader.include([
                '../lib/dojo/dojo/dojo.js.uncompressed'
            ]);
        });
    }
});

DojoLoader(DojoScriptLoader());