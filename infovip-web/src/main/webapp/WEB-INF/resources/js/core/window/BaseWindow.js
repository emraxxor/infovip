var BaseWindow = easejs.AbstractClass('BaseWindow').extend(Controller,{

	'protected window' : null,
	
	'protected title' : '',
	
	'private width' : 350,
	
	'private height' : 300,
	
	'private position' : 'center',

	'private resizable' : true,
	
	'private moveable' : true,
	
	'private modal' : true,
	
	/**
	 * If it is set to true then the window will not be displayed by the default window manager
	 */
	'protected doNotAppearByDefault' : false,
	
	'override virtual __construct' : function() {
		this.__super();
	},

	'public abstract beforeDisplay' : [],

	'public abstract views' : [],
	
	'public abstract onCreationComplete' : ['window','body'],
	
	'protected wListeners' : {},
	
	
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

    'public resize' : function() {
    	this.window.resize();
    },
    
    'public getWindow' : function() {
    	return this.window;
    },
    
    'public setModal' : function(v) {
    	this.modal = v;
    },
    
    'public addComponentListener' : function(type,func) {
    	this.wListeners[type] = func;
    },
    
    'public addCreationCompleteEvent' : function(func) {
    	this.wListeners['creationComplete'] = func;
    },
    
	'public virtual create' : function() {
		var that = this;
		this.window = webix.ui({
			view : "window",
			height:this.width,
			width:this.height,
		    position : this.position,
		    resize : this.resizable,
		    move: this.moveable,
		    modal: this.modal,
		    head:{
				view:"toolbar", margin:-4, cols:[
					{ view:"label", label: this.title },
					{ view:"icon", icon:"fas fas-times-circle fa-times-circle", on : { "onItemClick": function(id,e) { that.close(); } } }
				]
			},
			body: {
					view : "form",
					elements : []
			}
			
		});
		webix.UIManager.removeHotKey("esc");
	},
	
	'public virtual onComponentCreationComplete' : function() {
		if ( this.wListeners['creationComplete'] != undefined ) {
			this.wListeners('creationComplete')(this);
		}
	},
	
	'public virtual close' : function() {
		this.window.close();
	},
    
    'public virtual display' : function() {
    	if ( !this.doNotAppearByDefault ) {
    		this.window.show();
    	}
    	
    	// workaround
    	this.window.config.width = this.width;
    	this.window.config.height = this.height;
    	this.window.resize();
    	this.onCreationComplete(this.window,this.window.getBody());
    	this.onComponentCreationComplete();
    }
});
