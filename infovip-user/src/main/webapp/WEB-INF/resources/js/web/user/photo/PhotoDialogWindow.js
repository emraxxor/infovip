/**
 * The window that is required to view the image.  
 * It provides the opportunity to make a new comment to the image, it also ensures the opportunity to add new likes to the picture. 
 * 
 * @author Attila Barna
 */
var PhotoDialogWindow = easejs.Class('PhotoDialogWindow').extend(BaseModalDialogWindow,{

	'public static DIALOG_WINDOW' : ApplicationScope.config.RESOURCES_PATH + '/mst/media/PhotoDialogWindow.mst',

	'private controller' : null,
	
	'private data' : null,

	'private event' : null,
	
    'override virtual __construct' : function(o,d,e) {
    	this.__super( PhotoDialogWindow.$('DIALOG_WINDOW') );
    	this.controller = o;
    	this.data = d;
    	this.event = e;
    	this.data.title = this.data.title == "" ? "No title" : this.data.title;
    	this.setTitle(this.data.title);
    	this.setWidth(60);
    	this.setHeight(60);
    	this.setTop(20);
     },
     
 	'public override display' : function () {
 		this.__super();
 		
	 },
	 
	'private setUserInfo' : function() {
		const u = CurrentUser.info();
		const img = WEB_DIR + "/user/image?" + u.picture ;
		const udiv = this.getBody().querySelector("div.user");
		udiv.querySelector("img").src = img;
		udiv.querySelector("p.name") .append(u.userName);
		udiv.querySelector("p.name") .append(u.userName);
		udiv.querySelector("p.date").prepend(u.lastSeen);
	},
	 
	'public override onComponentCreationComplete' : function() {		
		this.getBody().querySelector("img.photo").src = WEB_DIR + "/user/media-image?" + this.data.data + "_BIG"; 
		this.getBody().querySelector("p.figcaption").append(this.data.title);
		this.setUserInfo();
	 },

	'public override events' : function() {
		
	 },
	 

});
