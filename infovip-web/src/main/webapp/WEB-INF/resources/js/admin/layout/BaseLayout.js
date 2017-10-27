/**
 * @author attila 
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
    	this.webixui.show();
    }

});

//# sourceURL=/resources/js/admin/layout/BaseLayout.js
