/**
 * 
 */

var DefaultHTMLFormValidator = easejs.Class('DefaultHTMLFormValidator').extend(Controller,{

	'protected controller' : null,

	'protected fields' : null,

	'protected rules' : [],
	
	'override virtual __construct' : function(controller) {
		this.controller = controller;
	},
	
	'public virtual getController' : function() {
		return this.controller;
	},
	
	'public virtual filter' : function(f) {
		return this.fields.filter(f);
	},
	
	'public setRules' : function(v) {
		this.rules = v;
	},
	
	'public getRules' : function() {
		return this.rules;
	},
	
	'public getFields' : function() {
		return this.fields;
	},
	
	'public setFields' : function(v) {
		this.fields = v;
	},
	
	'public virtual addClass' : function(field,className) {
		this.getController().getContainer().find('input[name='+field+']').addClass(className);
	},

	'public virtual removeClass' : function(field,className) {
		this.getController().getContainer().find('input[name='+field+']').removeClass(className);
	},
	
	'public virtual removeMessage' : function(field) {
		this.getController().getContainer().find('[ucomponent=warn_'+field+']').html(''); 
		this.getController().getContainer().find('input[name='+field+']').html('');
	},
	
	'public virtual addMsg' : function(field,msg) {
		var c = this.getController().getContainer().find('[ucomponent=warn_'+field+']') ;
		var o = this.getController().getContainer().find('input[name='+field+']');
		
		if ( c.length != 0 ) {
			c.html(msg);
		} else {
			o.after(jQuery('<span></span>',{ucomponent: 'warn_' + field , html : msg , class: 'warn-message' }));
		}
	},
	
	'public virtual existsField' : function(fieldName) {
		return this.filter(function(k,o){ if ( o.field == fieldName ) { return true; }  }).length != 0;
	},

	'public virtual getField' : function(fieldName) {
		return this.filter(function(k,o){ if ( o.field == fieldName ) { return true; }  })[0];
	},

	'public virtual validate' : function() {
		var result = true;
		this.fields = this.getController().getContainer().find('input').map(function(k,o){ return { field : o.name , value: o.value } });
		
		for(var i=0; i < this.rules.length; i++) {
			var res = this.rules[i].validate(this);
			if ( result == true && res == false ) {
				result = false;
			}
		}
		
		return result;
	}


});