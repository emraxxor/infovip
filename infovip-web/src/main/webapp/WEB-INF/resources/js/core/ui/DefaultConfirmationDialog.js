/**
 *  @author attila
 */
var DefaultConfirmationDialog = easejs.Class('DefaultConfirmationDialog').extend({
	
	'private title' : null,
	
	'virtual __construct' : function(title) {
		this.title = title || __tr('msg.confirmation');
	},

	'display' : function(message,callback,params) {
		DefaultDialogWindow.showMessageDialog(this.title,message,DefaultDialogWindow.$('CONFIRM_MESSAGE'),function(cparam,window){
			callback(cparam,window);
			window.hide();
		},params);
	}
	
});
