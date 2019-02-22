/**
 * ApplicationScope.config.WEB_ROOT
 * 
 *  @author attila
 */

var GeneralSettingsForm = easejs.Class('GeneralSettingsForm').extend(DefaultHTMLForm,{

	'public static HANDLER' : {
		UPDATE : ApplicationScope.config.WEB_ROOT + '/user/settings/general/update'
	},

	'override virtual __construct' : function(container) {
        this.__super(container);
        this.setValidator(new GeneralSettingsValidator(this));
	},
	
	'override public virtual onSuccessfulEvent' : function(data) {
		var that = this;
	},
	
	'override public virtual setSubmitEventLister' : function() {
    	this.getForm().find('[data-type=submit]').on('click', { data : this} , this.onSubmitClick );
	}, 

	
	'override public virtual onSubmitClick' : function(e) {
		var that = e.data.data;
		var container = that.getContainer();
		var data = that.getValues();
		
		if ( that.getValidator().validate() ) {
			 	data['g-recaptcha-response'] = container.find('#g-recaptcha-response').val();
			 	var w = DefaultInformationDialog().display(__tr('msg.loading'));
				that.async( GeneralSettingsForm.$('HANDLER').UPDATE , data , function( data , o ) {
					DefaultFormValidatorHandlerDialog(data).display();
					w.hide();
				} , that );
		 }
		
	},
});