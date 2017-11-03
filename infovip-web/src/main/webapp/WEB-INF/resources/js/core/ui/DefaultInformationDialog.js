/**
 *  @author attila
 */
var DefaultInformationDialog = easejs.Class('DefaultInformationDialog').extend({
	
	'private title' : null,
	
	'virtual __construct' : function(title) {
		this.title = title || __tr('msg.information');
	},

	'display' : function(message,callback,params) {
		return DefaultDialogWindow.showMessageDialog(this.title,message,DefaultDialogWindow.$('INFORMATION_MESSAGE'),function(cparam,window){},params);
	}
	
});
