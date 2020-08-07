/**
 * @author Attila Barna
 */
var UIController = easejs.Class('UIController').extend(Controller,{
  
	'override virtual __construct': function () {
		this.__super();
	 },

	
	'public virtual postConstruct' : function() {}, 

	'public virtual events' : function() {}, 
	
	'public virtual render' : function() {},
	
	'public virtual preDestroy' : function() {}, 
	
	'public virtual destroy' : function() {}, 

	'public virtual onCreationComplete' : function() {}, 
});
