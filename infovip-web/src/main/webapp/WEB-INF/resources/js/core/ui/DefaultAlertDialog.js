/**
 *  @author Attila Barna
 */
var DefaultAlertDialog = easejs.Class('DefaultAlertDialog').extend({
	
	'private title' : null,
	
	'virtual __construct' : function(title) {
		this.title = title || __tr('msg.information');
	},

	'display' : function(message,callback,params) {
		return DefaultDialogWindow.showMessageDialog( 
				this.title,
				__tr(message),
				DefaultDialogWindow.$('ALERT_MESSAGE'),
				function(cparam,window){callback(cparam,window);},
				params
		);
	}
	
});
