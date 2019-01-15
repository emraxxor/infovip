/**
 *  @author Attila Barna
 */
var DefaultConfirmationDialog = easejs.Class('DefaultConfirmationDialog').extend({
	
	'private title' : null,
	
	'private listeners' : {},
	
	'virtual __construct' : function(title) {
		this.title = title || __tr('msg.confirmation');
	},
	
	'public cancel' : function(params,dWindow) {
		if ( this.listeners['cancel']['func'] != undefined ) {
			this.listeners['cancel']['func']( params, dWindow );
		}
	 },
	 
	'public addEventListener' : function(name,func) {
		if ( this.listeners[name] == undefined ) 
			this.listeners[name] = {};
		
		this.listeners[name]['func'] = func;
		
		return this;
	 },

	'display' : function(message,callback,params) {
		var that = this;
		DefaultDialogWindow.showMessageDialog(
				this.title,
				message,
				DefaultDialogWindow.$('CONFIRM_MESSAGE'),
				function(cparam,window){
					callback(cparam,window);
					window.hide();
				},
				params,
				function(params,dWindow) {
					that.cancel(params,dWindow);
				}
		);
	}
	
});
