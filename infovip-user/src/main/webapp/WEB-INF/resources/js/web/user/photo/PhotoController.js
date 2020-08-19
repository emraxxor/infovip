/**
 * 
 * @author Attila Barna
 */
var PhotoController = easejs.Class('PhotoController').extend(UIController,{

	'private photoWall' : null,
	
    'override virtual __construct' : function() {
    	this.photoWall = new PhotoWall('mediawaterfall');
     },

	'public override virtual events' : function() {
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.photoWall.display();
	 }

});
