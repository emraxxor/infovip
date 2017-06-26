/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global DojoComponentExecutor, easejs, DojoComponent, $jquery, dijit */

document.write("OK");

var TestDojo = easejs.Class('TestDojo').extend(DojoComponent, {
    'private button': null,
    'private dialog': null,
    'private jqDialog': null,
    'private btnId': 'testbutton',
    'private dialogId': 'testDialog',
    'public __construct': function () {
    },
    'public init': function () {
        var _oself = this;
        this.button = new dojo.dijit.form.Button({
            label: "Show me!",
            onClick: function (ev) {
                _oself.defaultOnClickHandler(ev);
            }
        });

        this.dialog = new dojo.dijit.Dialog({
            title: "Dialog",
            style: "width: 300px;height:400px;"
        });

        this.jqDialog = $jquery('#' + this.dialogId).dialog({
            autoOpen: false,
            title: 'Test',
            width: 500,
            height: 300,
            show: {
                effect: "blind",
                duration: 1000
            },
            hide: {
                effect: "explode",
                duration: 1000
            }
        });

    },
    'public override preInit': function () {
        $jquery("body").append($jquery("<button></button>", {id: this.btnId}));
        $jquery("body").append($jquery("<div></div>", {id: this.dialogId, html: "Test message..."}));
    },
    'public requirements': function () {
        return  [
            "dojo",
            "dijit",
            "dijit/form/Button",
            "dijit/Dialog"
        ];
    },
    'public destroy': function () {},
    'public defaultOnClickHandler': function (ev) {
        this.dialog.set("content", "Dialog");
        this.dialog.show();
        this.jqDialog.dialog('open');
    },
    'public display': function () {
        this.button.placeAt(this.btnId);
    }
});

DojoComponentExecutor.execute(TestDojo());