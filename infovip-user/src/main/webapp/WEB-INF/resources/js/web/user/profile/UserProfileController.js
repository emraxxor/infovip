/**
 * 
 * @author Attila Barna
 */
var UserProfileController = easejs.Class('UserProfileController').extend(UIController,{
	
	 'private user' : null,
	
	 'private avatarBox' : null,
	 
     'override virtual __construct' : function() {
		this.user = CurrentUser.info();
		this.avatarBox = document.body.querySelectorAll(".cover--avatar")[0];
	 },
	
	'public override virtual events' : function() {
		this.avatarBox.addEventListener('click', x => this.onClickAvatar(x));
	 },
	 
	 'public onClickAvatar' : function(e) {
		 UserProfileAvatarEditorWindow(e.target, this.user).display();
	 },
	 
    'public override virtual onCreationComplete' : function() {
    	this.__super();
	 }
});
