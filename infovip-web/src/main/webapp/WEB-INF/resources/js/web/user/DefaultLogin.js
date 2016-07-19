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

/**
 * Implementation for LoginIface
 * 
 * This is the first implentation for the login page.
 * It simply passes the arguments to the Login servlet then the servlet redirects
 * the user to a new location that depends on the login method.
 * 
 * By default the login method is handled by the Login servlet.
 * 
 */
var DefaultLogin = easejs.Class('DefaultLogin').implement(LoginIface, DefaultFormInterface).extend({
    
    /**
     * Current webix object
     */
    'private webixObject': null,
    
    /**
     * The id of the div where the form is displayed
     */
    
    'private defaultLoginFormID': 'default-login-form',
    
    /**
     * Id of the current form
     */
    
    'private loginFormID': 'login-form-id',
    __construct: function () {},
    /**
     * Submits form
     * @returns {undefined}
     */
    'public onSubmit': function ()
    {
        // if the validation is success
        if (this.getFormView().validate()) {
            webix.send("login", this.getFormView().getValues(), "POST");
        }
    },
    'public displayForm': function ()
    {
        this.webixObject.show();

    },
    /**
     * Creates the default login form
     * @returns {undefined}
     */
    'public onCreate': function () {
        this.webixObject = webix.ui({
            container: this.defaultLoginFormID,
            id: this.loginFormID,
            view: "form",
            scroll: false,
            width: 500,
            height: 300,
            elements: [{
                    rows: [
                        {view: "template", template: "Log in", type: "header"},
                        {view: "text", label: 'Username:', name: "userName"},
                        {view: "text", type: 'password', label: 'Password:', name: "userPassword"},
                        {view: "button", type: 'form', value: "Login", click: this.onSubmit}
                    ]
                }
            ],
            rules: {
                "userName": webix.rules.isNotEmpty,
                "userPassword": webix.rules.isNotEmpty,
            },
            elementsConfig: {
                labelAlign: "left",
                labelWidth: 90
            }
        });
    },
    'public onClose': function () {

    },
    'public static create': function () {
        return DefaultLogin();
    }
});


