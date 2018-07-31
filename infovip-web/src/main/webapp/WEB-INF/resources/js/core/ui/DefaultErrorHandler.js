/**
 * 
 */
var DefaultErrorHandler = easejs.Class('DefaultErrorHandler').extend({
	
	'private errorObject' : null,
	
	'virtual __construct' : function(object) {
		this.errorObject = object;
	},

	'display' : function() {
		DefaultDialogWindow.showMessageDialog(__tr('msg.error'), __tr(this.errorObject.message),DefaultDialogWindow.$('CONFIRM_MESSAGE'),function(params,window){
			window.hide();
		},{});
	}
	
});
