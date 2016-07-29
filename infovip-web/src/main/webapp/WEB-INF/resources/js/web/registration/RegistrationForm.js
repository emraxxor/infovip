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
 * Creates a basic form for registration.
 * By default the registration method is handled by the Registration servlet.
 */
var RegistrationFrom = easejs.Class('RegistrationFrom').implement(LoginIface, DefaultFormInterface).extend({
    /**
     * Current webix object
     */
    'private webixObject': null,
    /**
     * The id of the div where the form is displayed
     */

    'private defaultRegistrationFormID': 'default-registration-form',
    /**
     * Id of the current form
     */

    'private registrationFormID': 'registration-form-id',
    __construct: function () {},
    /**
     * Submits form
     * @returns {undefined}
     */
    'public onSubmit': function ()
    {
        // if the validation is success
        if (this.getFormView().validate()) {
            var res = AjaxManager.send("registration/add", this.getFormView().getValues());
            webix.alert({
                title: "Notice: ",
                type: "alert-warning",
                text: res.status + " : " + res.statusMessage,
                ok: "OK"
            });
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
        var _oself = this;
        this.webixObject = webix.ui({
            container: this.defaultRegistrationFormID,
            id: this.registrationFormID,
            view: "form",
            scroll: false,
            width: 800,
            height: 500,
            elements: [{
                    rows: [
                        {view: "template", template: "Registration", type: "header"},
                        {view: "text", label: Translator.tr('uname'), name: "uname"},
                        {view: "text", type: 'password', label: 'Password:', name: "upassword"},
                        {view: "text", type: 'password', label: 'Password again:', name: "upasswordre"},
                        {view: "text", label: 'Email:', name: "umail"},
                        {view: "button", type: 'form', value: "Login", click: this.onSubmit}
                    ]
                }
            ],
            rules: {
                "uname": webix.rules.isNotEmpty,
                "upassword": function (value) {
                    return webix.rules.isNotEmpty(value) && _oself.webixObject.getValues()['upasswordre'] == value;
                },
                "umail": webix.rules.isEmail
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