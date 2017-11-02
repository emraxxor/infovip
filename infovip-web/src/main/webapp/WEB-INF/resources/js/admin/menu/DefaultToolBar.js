/**
 *  @author attila
 */
var DefaultToolBar = easejs.Class('DefaultToolBar',{
	
	'virtual __construct' : function() {
		
	},
	
	'public static create' : function() {
		return new DefaultToolBar();
	},
	
	
	'public virtual schema' : function() {
		return {   
			view: "toolbar", 
			padding:3, 
			elements: [
				{
						view: "button", type: "icon", icon: "bars",
						width: 37, align: "left", css: "app_button", 
						click: function(){
								$$(ADMIN_DEFAULT_SIDEBAR_ID).toggle();
								$$(ADMIN_DEFAULT_LAYOUT_BODY).resize();
						}
				},
				{ view: "label", label: "Admin page"},
				{},
				{ view: "button", type: "icon", width: 45, css: "app_button", icon: "envelope-o",  badge:4},
				{ view: "button", type: "icon", width: 45, css: "app_button", icon: "bell-o",  badge:10}
			]
		};
	}

});

//# sourceURL=resources/js/admin/layout/DefaultToolBar.js