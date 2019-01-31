/**
 *  @author Attila Barna
 */
var DefaultFormValidatorHandlerDialog = easejs.Class('DefaultFormValidatorHandlerDialog').extend({
	
	'private statusObject' : null,
	
	'virtual __construct' : function(object) {
		this.statusObject = object;
	},

	'public virtual isValid' : function() {
		var success = 0;
		for(var i=0; i < this.statusObject.length; i++ ) {
			var o = this.statusObject[i];
			if ( o.validationType == 'VALIDATION_SUCCESSFUL' ) {
				success++;
			}
		}
		return success == this.statusObject.length;
	},
	
	'public virtual getStatusObject' : function() {
		return this.statusObject;
	},
	
	'public virtual display' : function(func,fParams) {
		var that = this;
		var response = "";
		var valid = this.isValid();
		
		for(var i=0; i < this.statusObject.length; i++ ) {
			var o = this.statusObject[i];

			if ( !valid &&  o.validationType == 'VALIDATION_SUCCESSFUL' ) continue;
			
			response += o.message + "<br/>";
		}
		
		if ( valid ) {
			DefaultDialogWindow.showMessageDialog(__tr('msg.success'), response ,DefaultDialogWindow.$('CONFIRM_MESSAGE'),function(params,window){
				if ( func !== undefined ) 
					func(fParams,that);
				
				window.hide();
			},{});
		} else {
			DefaultDialogWindow.showMessageDialog(__tr('msg.error'),  response ,DefaultDialogWindow.$('CONFIRM_MESSAGE'),function(params,window){
				if ( func !== undefined ) 
					func(fParams,that);
				
				window.hide();
			},{});
		}
	}
	
});
