/**
 * 
 * @author Attila Barna
 */
var MediaController = easejs.Class('MediaController').extend(UIController,{

	'private btnNewAlbum' : null,
	
	'private mediaPageWall' : null,
	
    'override virtual __construct' : function() {
    	this.mediaPageWall = new MediaPageWall('mediawaterfall');
    	this.btnNewAlbum = document.querySelector('.btn-new-album');
     },

	'public override virtual events' : function() {
		this.btnNewAlbum.addEventListener('click' , x => UIControllerExecutor(  MediaAlbumDialogWindow() ).execute() );
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.mediaPageWall.display();
	 }

});
