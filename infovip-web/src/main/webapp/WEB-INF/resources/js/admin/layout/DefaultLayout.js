/**
 * @author attila
 */
var DefaultLayout = easejs.Class('DefaultLayout').extend(BaseLayout,{

		'private defaultAdminMenu' : null,
	
		'override virtual __construct' : function() {
			this.__super();
		},

		'public body' : function() {
			return {
				rows : [
						{
							height : 49,
							id : "title",
							css : "title",
							template : "<div id='admin:main:panel' style='display:inline-flex;'><div class='admin-header'>#title#</div><div style='margin-left:20px;' class='admin-details'>( #details# )</div></div>",
							data : {
								text : "",
								title : "",
								details : "",
							}
						},
						{
							view : "scrollview",
							scroll : "native-y",
							autoheight: true,
							body : {
								cols : [ {
									template : "<div id='admin:default:admin-body-content'></div>"
								} ]
							}
						} 
					]
			};
		},

		'public menu' : function() {
			return DefaultAdminMenu.create().schema();
		},
		

		'public mainToolBar' : function() {
			return DefaultToolBar.create().schema();
		}
});

//# sourceURL=/resources/js/admin/layout/DefaultLayout.js