/**
 *  @author attila
 */
var DefaultStatusHandler = easejs.Class('DefaultStatusHandler').extend({
	
	'private statusObject' : null,
	
	'virtual __construct' : function(object) {
		this.statusObject = object;
	},

	'display' : function() {
		DefaultDialogWindow.showMessageDialog(__tr('msg.success'),this.statusObject.message,DefaultDialogWindow.$('CONFIRM_MESSAGE'),function(params,window){
			window.hide();
		},{});
	}
	
});
