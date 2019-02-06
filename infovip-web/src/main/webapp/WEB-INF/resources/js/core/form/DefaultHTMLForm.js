/**
 * @author Attila Barna
 */
var DefaultHTMLForm = easejs.Class('DefaultHTMLForm').extend(Controller,{

	'protected container' : null,
	
	
	'protected validator' : null,
	
	'override virtual __construct' : function(container) {
		this.container = container;
	},
	
	'public getContainer' : function() {
		return this.container;
	},
	
	'public getValidator' : function() {
		return this.validator;
	},
	
	'public setValidator' : function(v) {
		this.validator = v;
	},
	
	'public getInputField' : function(fieldName) {
		return this.container.find("input[name="+fieldName+"]");
	 },

	
	'public getValues' : function() {
		var data = {};
		var inputs = this.container.find('input');
		
		for(var i=0; i < inputs.length; i++ ) 
			data[inputs[i].name] = inputs[i].value; 
		
		return data;
	 },
	 
	'public virtual getForm' : function() {
			return jQuery( this.container.find('form').get() );
	 },
	 
	'public virtual setSubmitEventLister' : function() {throw new Error('Not implemented!');}, 
	 
	'public setValues' : function(o) {
    	for (var field in o ) {
    	    if (o.hasOwnProperty(field)) {
    	    	 this.container.find("[name="+field+"]").val(o[field]);
    	    }
    	}
	 },

	'public virtual onSuccessfulEvent' : function(data) { throw new Error('Not implemented!'); },

	'public virtual onSubmitClick' : function(event) { throw new Error('Not implemented!'); }
});
