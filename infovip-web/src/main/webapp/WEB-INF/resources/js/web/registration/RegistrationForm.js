/**
 * ApplicationScope.config.WEB_ROOT
 * 
 *  @author attila
 */

var RegistrationForm = easejs.Class('RegistrationForm').extend(DefaultHTMLForm,{

	'public static HANDLER' : {
		ADD : ApplicationScope.config.WEB_ROOT + '/registration/add'
	},

	'override virtual __construct' : function(container) {
        this.__super(container);
        this.setValidator(new RegistrationValidator(this));
	},
	
	'override public virtual onSuccessfulEvent' : function(data) {
		var that = this;
	},
	
	'override public virtual onSubmitClick' : function(e) {
		var that = e.data.data;
		var data = {};
		var container = that.getContainer();
		var inputs = container.find('input');
		
		
		for(var i=0; i < inputs.length; i++ ) 
			data[inputs[i].name] = inputs[i].value;

		 if ( that.getValidator().validate() ) {
			 	data['g-recaptcha-response'] = container.find('#g-recaptcha-response').val();
				var w = DefaultInformationDialog().display(__tr('msg.loading'));
				that.async( RegistrationForm.$('HANDLER').ADD , data , function( data , o ) {
					DefaultFormValidatorHandlerDialog(data).display();
					w.hide();
				} , that );
		 }
		
	},
});