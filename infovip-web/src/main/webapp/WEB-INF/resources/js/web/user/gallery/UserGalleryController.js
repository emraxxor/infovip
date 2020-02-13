/**
 * 
 * @author Attila Barna
 */
var UserGalleryController = easejs.Class('UserGalleryController').extend(UIController,{
	
	'private controller' : null,
	
	'override virtual __construct' : function() {
		this.controller = new ActivityPostController();
	},
	
    'public override virtual onCreationComplete' : function() {
    	new UIControllerExecutor(  this.postController ).execute(); 
	}
	
});
