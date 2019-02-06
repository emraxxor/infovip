/**
 * 
 * @author Attila Barna
 */
var GeneralSettingsController = easejs.Class('GeneralSettingsController').extend(UIController,{
	
	 'private user' : null,
	 
	 'private form' : null,
	
     'override virtual __construct' : function(container) {
		this.user = CurrentUser.info();
		this.form = new GeneralSettingsForm(container);
	 },
	
    'public override virtual onCreationComplete' : function() {
    	this.__super();
    	this.form.setValues(this.user);
    	this.form.setSubmitEventLister();
	 }
});
