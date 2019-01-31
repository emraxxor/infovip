/**
 * @author Attila Barna 
 */
var ActivityCommentBox = easejs.Class('ActivityCommentBox').extend(UIController,{
	
	'private postId' : null,
	
	'private node' : null,
	
	'private panel' : null,
	
	'private editor' : null,
	
	'override virtual __construct' : function(postId,node) {
			this.__super();
			this.postId = postId;
			this.node = node;
			this.editor = new TinyMceEditor();
			this.panel = jQuery("<div></div>",  { "data-id" : postId,  'class' : "activity-post-comment" } );
	 },
	 
	 
	'public override postConstruct' : function() {
		this.node.parent().parent().append( this.panel );
	 },
	 
	'public override render' : function() {
		 this.panel.append(this.editor.getNode());
		 this.editor.basic('80%',30);
	 },
	 
	'public getNode' : function() {
		return this.node;
	 },
	 
	'public getPostId' : function() {
		return this.postId; 
	 },
	 
	'public override onCreationComplete' : function() {
		 
	 },
	 
	 'public getNode' : function() {
		 return this.node;
	 }
	
});
