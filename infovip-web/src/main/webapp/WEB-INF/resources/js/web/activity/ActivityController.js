/**
 * 
 * @author Attila Barna
 */
var ActivityController = easejs.Class('ActivityController').extend(UIController,{
	
	'private postController' : null,
	
	'override virtual __construct' : function() {
		this.postController = new ActivityPostController();
	},

	
    'public override virtual onCreationComplete' : function() {
    	new UIControllerExecutor(  this.postController ).execute(); 
	}
	
});
