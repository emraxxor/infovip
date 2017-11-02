/**
 * @author attila 
 */
var DefaultAdminMenu = easejs.Class('DefaultAdminMenu',{
	
	'virtual __construct' : function() {
		
	},
	
	'public static create' : function() {
		return new DefaultAdminMenu();
	},
	
	'public virtual data' : function() {
		return [
			{id: "dashboard", icon: "dashboard", value: "Dashboards",  
				data:[
					{ id: "dashboard1", value: __tr('admin.menu.summary') , servlet : "dashboard"}
			]},
		];
	},
	
	'public virtual schema' : function() {
		return 	{
			view: "sidebar",
			id: ADMIN_DEFAULT_SIDEBAR_ID,
			data: this.data(),
			on:{
				onAfterSelect: function(id){
					self.location.href = ADMIN_PATH + '/' + this.getItem(id).servlet;
				}
			}
		}
	}

});

//# sourceURL=resources/js/admin/menu/DefaultAdminMenu.js
