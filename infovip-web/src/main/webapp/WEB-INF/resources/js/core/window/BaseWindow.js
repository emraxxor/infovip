var BaseWindow = easejs.AbstractClass('BaseWindow').extend(Controller,{

	'protected window' : null,
	
	'private title' : '',
	
	'private width' : 350,
	
	'private height' : 300,
	
	'private position' : 'center',

	'private resizable' : true,
	
	'private moveable' : true,
	
	'override virtual __construct' : function() {
		this.__super();
	},

	'public abstract beforeDisplay' : [],

	'public abstract views' : [],
	
	'public abstract onCreationComplete' : ['window','body'],
	
	
    /**
     * Gets the index of the given component
     */
	'public indexOf' : function(component) {
		return this.window.getBody().index(component);
	},

	
    'public addView' : function(view,pos) {
    	if ( pos == undefined ) {
    		this.window.getBody().addView(view); 
    	} else {
    		this.window.getBody().addView(view,pos); 
    	}
    },
    
	'public removeView' : function(view) {
		this.window.getBody().removeView(view);
	},

	'public setPosition' : function(pos) {
		this.position = pos;
	},
	
	'public setTitle' : function(t) {
		this.title = t;
	},
	
	'public isResizable' : function(v) {
		this.resizable = v;
	},
	
	'public isMovable' : function(v) {
		this.movable = v;
	},
    
    'public setWidth' : function(w) {
    	this.width = w;
    },
    
    'public setHeight' : function(h) {
    	this.height = h;
    },
	
	'public virtual create' : function() {
		this.window = webix.ui({
			view : "window",
			height:this.width,
			width:this.height,
		    position : this.position,
		    resize : this.resizable,
		    move: this.moveable,
		    head:{
				view:"toolbar", margin:-4, cols:[
					{ view:"label", label: this.title },
					{ view:"icon", icon:"times-circle", on : { "onItemClick": function(id,e) { this.getTopParentView().close(); } } }
				]
			},
			body: {
					view : "form",
					elements : []
			}
			
		});
	},
	
	'public virtual close' : function() {
		this.window.close();
	},
    
    'public virtual display' : function() {
    	this.window.show();
    	// workaround
    	this.window.config.width = this.width;
    	this.window.resize();
    	this.onCreationComplete(this.window,this.window.getBody());
    }
});
