/**
 *  @author Attila Barna
 */
var DefaultWarningDialog = easejs.Class('DefaultWarningDialog').extend({
	
	'private title' : null,
	
	'virtual __construct' : function(title) {
		this.title = title || __tr('msg.information');
	},

	'display' : function(message,callback,params) {
		return DefaultDialogWindow.showMessageDialog(this.title,__tr(message),DefaultDialogWindow.$('WARNING_MESSAGE'),
				function(cparam,window){callback(cparam,window);}
		,params);
	}
	
});
