/**
 * @author Attila Barna 
 */
var ActivityCommentBox = easejs.Class('ActivityCommentBox').extend(UIController,{
	
	'private postId' : null,
	
	'private node' : null,
	
	'private panel' : null,
	
	'private editor' : null,
	
	'private button' : null,
	
	'public static HANDLER' : {
		ADD : WEB_DIR + "/activity/comment"
	 },
	 
	'public static TEMPLATE' : {
			COMMENT : ApplicationScope.config.RESOURCES_PATH + '/js/web/activity/ActivityComment.mst'
	},
	
	'private template' : null,
	
	'override virtual __construct' : function(postId,node) {
			this.__super();
			this.postId = postId;
			this.node = node;
			this.editor = new TinyMceEditor();
			this.template = this.load( ActivityCommentBox.$('TEMPLATE').COMMENT , {}, 'GET'  );
			this.panel = jQuery("<div></div>",  { "data-id" : postId,  'class' : "activity-post-comment" } );
			this.button = jQuery("<button></button>", { type : "button", 'class' : "btn btn-primary", html: __tr("Comment") } );
	 },
	 
	'public getButton' : function() {
		return this.button;
	 },
	 
	'public override postConstruct' : function() {
		this.node.parent().parent().find('[data-uid=commentbox]').append( this.panel );
	 },
	 
	'public override render' : function() {
		 this.panel.append(this.editor.getNode());
		 this.panel.append(this.button);
		 this.editor.basic('80%',30);
	 },
	 
	'public getNode' : function() {
		return this.node;
	 },
	 
	'public getEditor' : function() {
		return this.editor;
	 },
	 
	'public getPostId' : function() {
		return this.postId; 
	 },
	 
	'public onClick' : function(e) {
		var sender = e.data.data;
		
    	var w = DefaultInformationDialog().display(__tr('msg.loading'));
		sender.async( ActivityCommentBox.$('HANDLER').ADD , { id : sender.getPostId(),  text : sender.getEditor().getText() } , function( data , sender ) {
			w.hide();
			sender.addComment(data);
		} , sender );

	 },
	 
	'public getTemplate' : function() {
		 return this.template; 
	 },
	 
	'public addComment' : function(item) {
		this.getNode().parent().parent().find('ul.acomment--items').prepend( 
				Mustache.render( 
						   this.getTemplate() ,  {
							comments :  [
								item
							], 
						} 
				)
		);
		
		this.editor.clear();
	 },
	 
	'public override onCreationComplete' : function() {
		 this.button.on('click', { data: this }, this.onClick );
	 },
	 
	 'public getNode' : function() {
		 return this.node;
	 }
	
});
