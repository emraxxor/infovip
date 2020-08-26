/**
 * 
 * @author Attila Barna
 */
var PhotoController = easejs.Class('PhotoController').extend(UIController,{

	'private photoWall' : null,
	
	'private photoViewer' : null,
	
    'override virtual __construct' : function() {
    	this.photoWall = new PhotoWall('mediawaterfall', this);
    	this.photoViewer = new PhotoViewer(this);
     },

	'public override virtual events' : function() {
	 },
	 
	'public getViewer' : function() {
		return this.photoViewer;
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.photoWall.display();
	 }

});
