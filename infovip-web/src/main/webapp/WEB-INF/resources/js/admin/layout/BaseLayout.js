/**
 * @author Attila Barna 
 */
var BaseLayout = easejs.AbstractClass('BaseLayout').extend(Controller,{
	
	'protected webixui' : null,
	
    'override virtual __construct': function () {
    	this.webixui =  webix.ui({
			rows: [
				this.mainToolBar(),
				{
					cols:[
						this.menu(),
						this.body()
					]
				}
			]
		});
    	
    },
    
    'public abstract body' : [],
    'public abstract menu' : [],
    'public abstract mainToolBar' : [],
    
    
    'public display' : function() {
    	var that = this;
    	this.webixui.show();
    	webix.event(window, "resize", function(){  $$(ADMIN_DEFAULT_LAYOUT_BODY).adjust(); })
    }

});

//# sourceURL=/resources/js/admin/layout/BaseLayout.js
