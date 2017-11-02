/**
 *  @author attila
 */
var BaseForm = easejs.AbstractClass('BaseForm').extend(Controller,{

	'protected webixuiLayout' : null,
	
	'protected title' : 'Component title',
	
	'protected description' : 'Component description',
	
	'protected mainView' : null,
	
    'override virtual __construct': function () {
    	this.__super();
    	this.webixuiLayout = webix.ui({
    		rows : [
    			
    		]
    	});
    },
    
    'public abstract onBeforeInit' : [],
    
    'public abstract onCreationComplete' : ['ui'],

	'public setTitle' : function(title) {
		this.title = title;
	},
    
    'public setDescription' : function(description) {
    	this.description = description;
    },
    
    'public getTitle' : function() {
    	return this.title;
    },
    
    'public getDescription' : function() {
    	return this.description;
    },

    /**
     * Gets the index of the given component
     */
	'public indexOf' : function(component) {
		return this.webixuiLayout.index(component);
	},
    
    'public addView' : function(view,pos) {
    	if ( pos == undefined ) {
    		this.webixuiLayout.addView(view); 
    	} else {
    		this.webixuiLayout.addView(view,pos); 
    	}
    },
	
	'public removeView' : function(viewId) {
		this.webixuiLayout.removeView(viewId);
	},
	
	'public getMainView' : function() {
		return this.mainView;
	},
    
    'public display' : function() {
		jQuery("div[class=admin-header]").html(this.title);
		jQuery("div[class=admin-details]").html(this.description);
		this.mainView = webix.ui({container: ADMIN_BODY_PANEL, id: ADMIN_DEFAULT_LAYOUT_BODY , rows: [ ]});
		this.mainView.addView(this.webixuiLayout);
		this.onCreationComplete(this.mainView);
		
    }

});