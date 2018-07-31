/**
 * @author Attila Barna
 */
var DefaultHandlerAdapter = easejs.AbstractClass('DefaultHandlerAdapter').extend(Controller,{

	'protected handlerURL' : null,
	
    'override virtual __construct': function (handlerURL) {
    	this.handlerURL = handlerURL;
    },
    
    'public getHandler' : function() {
    	return this.handlerURL;
    },
    
    'public setHandler' : function(val) {
    	this.handlerURL = val;
    	return this;
    },
   
    'public virtual postConstruct' : function() {},
    
    'public virtual preDestroy' : function() {},
    
    'public abstract onCreationComplete' : [],

});