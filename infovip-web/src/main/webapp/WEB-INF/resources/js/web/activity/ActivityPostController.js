/**
 * @author Attila Barna 
 */
var ActivityPostController = easejs.Class('ActivityPostController').extend(UIController,{
	
	'private form' : null,
	
	'private manager' : null,
	
	'private container' : null,
	
	'private button' : null,
	
	'private wall' : null,
	
	'private editor' : null,
	
	'public static HANDLER' : {
			ADD : WEB_DIR + "/activity/add"
	 },
	
	'override virtual __construct' : function() {
		this.manager = new TinyMceManager();
		this.editor = new TinyMceEditor();
		this.wall = new ActivityWall(jQuery("div[class=activity--list]"));
		this.container = jQuery("[data-uid=post-input-area]");
		this.button = jQuery("button[id=send-post]");
	},
	
    'public getText' : function() {
    	return this.editor.getText();
	},
	
	'public clear' : function() {
		return this.editor.clear();
	 },

    'public onSubmitClick' : function(e) {
    	var controller = e.data.data;
    	var w = DefaultInformationDialog().display(__tr('msg.loading'));
		controller.async( ActivityPostController.$('HANDLER').ADD , { text : controller.getText() } , function( data , o ) {
			w.hide();
			controller.addText(controller.getText());
		} , controller );
	 },
	 
	'public addText' : function(text) {
		this.wall.addItem(text);
		this.clear();
 	 },
	
    'public override virtual onCreationComplete' : function() {
    	this.container.append(this.editor.getNode());
    	this.editor.basic();
    	this.button.on('click', { data : this }, this.onSubmitClick );
    	new UIControllerExecutor(  this.wall ).execute(); 
	}
	
});
