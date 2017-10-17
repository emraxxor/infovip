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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
var BasicFormDisplayer = easejs.Class('BasicFormDisplayer').implement(Runnable).extend({
	
	/**
	 * @var DefaultFormInterface
	 */
    'private form': null,
    
    __construct: function (form) {
        this.form = form;
    },
    'public getForm': function () {
        return this.form;
    },
    'public load': function (parent) {
        this.form = parent.getForm();
        if (easejs.Class.isA(DefaultFormInterface, this.form)) {
            this.form.onCreate();
            this.form.displayForm();
            this.form.onCreationComplete();
        } else {
            throw new TypeError("Not supported type.");
        }
    },
    'public run': function () {
        var parent = this;
        jQuery(document).ready(function () {
        	parent.load(parent)
        });
    }
});


