/**
 * Default directives for administration interface
 * @author attila
 */


var ADMIN_DEFAULT_SIDEBAR_ID = "admin:admin-default-sidebar";
var ADMIN_BODY_PANEL = "admin:default:admin-body-content";
var ADMIN_PATH = ApplicationScope.config.WEB_ROOT + '/' + 'admin';

webix.protoUI({name : "cdatatable",}, webix.ui.datatable, webix.ActiveContent);
webix.protoUI({name : "ctreetable",}, webix.ui.treetable, webix.ActiveContent);



webix.editors.defaultParameterEditor = {
	
		focus : function() {
			this.getInputNode(this.node).focus();
			this.getInputNode(this.node).select();
		},
		getValue : function() {
			return this.getInputNode(this.node).value;
		},
		setValue : function(value) {
				var input = this.getInputNode(this.node);
				input.value = value;
		},
		getInputNode : function() {
				return this.node.firstChild;
		},
		render : function() {
			return webix.html.create("div", {
				"class" : "webix_dt_editor"
			}, "<input type='text'>");
		},
};

