/**
 * @author Attila Barna 
 */
var ActivityCommentBox = easejs.Class('ActivityCommentBox').extend(UIController,{
	
	'private postId' : null,
	
	'private node' : null,
	
	'private panel' : null,
	
	'private editor' : null,
	
	'private button' : null,

	'private submitHandler' : null,
	
	'private submitOptions' : null,
	
	'private replyTemplate' : null,
	
	'public static HANDLER' : {
			ADD   : WEB_DIR + "/activity/comment",
			REPLY : WEB_DIR + "/activity/reply"
	 },
	 
	'public static TEMPLATE' : {
			COMMENT : ApplicationScope.config.RESOURCES_PATH + '/js/web/activity/ActivityComment.mst',
			REPLY   : ApplicationScope.config.RESOURCES_PATH + '/js/web/activity/ActivityReply.mst'
	 },
	
	'private commentTemplate' : null,
	
	'override virtual __construct' : function(postId,node) {
			this.__super();
			this.submitHandler = null;
			this.submitOptions = null;
			this.postId = postId;
			this.node = node;
			this.editor = new TinyMceEditor();
			this.commentTemplate = this.load( ActivityCommentBox.$('TEMPLATE').COMMENT );
			this.replyTemplate = this.load( ActivityCommentBox.$('TEMPLATE').REPLY );
			this.panel = jQuery("<div></div>",  { "data-id" : postId,  'class' : "activity-post-comment" } );
			this.button = jQuery("<button></button>", { type : "button", 'class' : "btn btn-primary", html: __tr("Comment") } );
	 },
	 
	'public getButton' : function() {
		return this.button;
	 },
	 
	'public override postConstruct' : function() {
		jQuery(this.node.parent().parent().find('[data-uid=commentbox]')[0]).append( this.panel );
	 },
	 
	'public override render' : function() {
		 this.panel.append(this.editor.getNode());
		 this.panel.append(this.button);
		 this.editor.basic('80%',30);
	 },
	 
	'public onSubmitEvent' : function(f,options) {
		this.submitHandler = f;
		this.submitOptions = options;
	 },
	 
	'public getSubmitEvent' : function() {
		return this.submitHandler;
	 },
	 
	'public getSubmitOptions' : function() {
			return this.submitOptions;
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
	 
	'public getPanel' : function() {
		 return this.panel;
	 },
	 
	'public onClick' : function(e) {
		var sender = e.data.data;
		
    	if ( sender.getSubmitEvent() != null ) {
    		var f = sender.getSubmitEvent();
    		f(sender, sender.getSubmitOptions());
    	} 
    	
    	sender.getPanel().remove();
	 },
	 
	'public getCommentTemplate' : function() {
		 return this.commentTemplate; 
	 },
	
	'public getReplyTemplate' : function() {
		 return this.replyTemplate; 
	 },
	 
	'public addComment' : function(item) {
		this.getNode().parent().parent().find('ul.acomment--items').prepend( 
				Mustache.render( 
						   this.getCommentTemplate() , {
							dateFormat : function() {
									return function(text,render) {
										return moment(render(text)).format("YYYY-MM-DD HH:mm:ss" );
									}
							},
							comments :  [
								item
							], 
						} 
				)
		);
		
		this.editor.clear();
	 },
	 
	'public addReply' : function(item) {
			this.getNode().parent().parent().find('ul.acomment--items').prepend( 
					Mustache.render( 
							   this.getReplyTemplate() , {
								dateFormat : function() {
										return function(text,render) {
											return moment(render(text)).format("YYYY-MM-DD HH:mm:ss" );
										}
								},
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
	 }
	
});
