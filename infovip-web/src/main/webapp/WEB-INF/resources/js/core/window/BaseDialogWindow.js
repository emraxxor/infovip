/**
 *  @author Attila Barna
 */
var BaseDialogWindow = easejs.Class('BaseDialogWindow').extend(BaseWindow,{
	
	'protected parent' : null,
	
	
	'override virtual __construct' : function(parent) {
		this.__super();
		this.parent = parent;
	},
	
	
	'public setParent' : function(parent) {
		 this.parent = parent;
	} ,
	
	'public getParent' : function() {
		return this.parent;
	},
	
	/**
	 * The default submit event of the window.
	 * By default, it is not necessary to be used, 
	 * but it is recommended for easier handling
	 */
	'public virtual onSubmitEvent' : function(e) {},
	
	/**
	 * It is called after the original constructor has been invoked. 
	 * In this method the parent object is already available, so it can 
	 * be really useful when we need to do something in the parent component.
	 * 
	 */
	'public virtual postConstruct' : function() {},
	
	/**
	 * The default reload method
	 */
	'public virtual reload' : function(o) {},
	
	'public virtual beforeDisplay' : function() {},
	
	'public virtual views' : function() {},
	
	'public virtual onCreationComplete' : function(window,body) {},
	
	'public virtual redraw' : function() {
		this.getWindow().resize();
		this.getWindow().show(); 
	}
	
});
