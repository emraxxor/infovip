/**
 *  ApplicationScope.config.WEB_ROOT
 * 
 *  @author Attila Barna
 */

var UserCoverForm = easejs.Class('UserCoverForm').extend(DefaultHTMLForm,{

	'public static HANDLER' : {
	},

	'override virtual __construct' : function(container) {
        this.__super(container);
	},
	
	'override public virtual onSuccessfulEvent' : function(data) {
	
	},
	
	'override public virtual setSubmitEventLister' : function() {
	}, 
	
	
	'override public virtual render' : function() {
		
	},
	
	'public virtual onCoverClick' : function(e) {
		var that = e.data;
		var dialog = new MessageBasedModalWindow();
		
		dialog.setImage('/user/image/cover?default', 'Cover image');
		dialog.display();
	},
	
	'override public virtual listeners' : function() {
		this.addListener('click', this.onCoverClick);
	},

	
	'override public virtual onCreationComplete' : function() {
		
	},

	
	'override public virtual onSubmitClick' : function(e) {
		
	},
});