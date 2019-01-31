/**
 * 
 * @author Attila Barna
 */
var RegistrationController = easejs.Class('RegistrationController').extend(UIController,{
	
	'private container' : null,
	
	'private form' : null,
	
	'override virtual __construct' : function() {
		this.container = jQuery("form[name=registration]");
		this.form = new RegistrationForm(this.container);
	},

	
    'public override virtual onCreationComplete' : function() {
    	this.container.find('button[type=button]').on('click', { data : this.form }, this.form.onSubmitClick );
	}
	
});
