/**
 * 
 * @author Attila Barna
 */
var UserController = easejs.Class('UserController').extend(UIController,{
	
	 'private user' : null,
	
	 'private cover' : null,
	 
     'override virtual __construct' : function() {
		this.user = CurrentUser.info();
		this.cover = new UserCoverForm(jQuery("[data-type=cover-image]"));
	 },
	
    'public override virtual onCreationComplete' : function() {
    	this.__super();
    	DefaultHTMLFormExecutor.create(this.cover).execute();
	 }
});
