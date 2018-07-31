/**
 * 
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

	'public virtual onSuccessfulEvent' : function(data) { throw new Error('Not implemented!'); },

	'public virtual onSubmitClick' : function(event) { throw new Error('Not implemented!'); }
});
