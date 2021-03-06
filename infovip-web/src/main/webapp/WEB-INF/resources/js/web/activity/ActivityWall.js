/**
 * @author Attila Barna
 * @deprecated
 */
var ActivityWall = easejs.Class('ActivityWall').extend(IScroll,{

	'public static HANDLER' : {
		DATA : WEB_DIR + "/activity/data",
		LIKE : WEB_DIR + "/activity/like",
		NOLIKE : WEB_DIR + "/activity/nolike"
	 },

	'public static TEMPLATE' : {
		ITEM : ApplicationScope.config.RESOURCES_PATH + '/js/web/activity/ActivityItem.mst'
	 },

	'private itemTemplate' : null,
	
	'private token' : null,
	
	'private data' : null,
	
	'private size' : 10,
	
	'private user' : null,
	
	'override virtual __construct' : function(node) {
		this.__super(node);
		this.itemTemplate = this.load(ActivityWall.$('TEMPLATE').ITEM);
		this.user = CurrentUser.info();
		
		debugger
		
		if ('scrollRestoration' in history) 
			  history.scrollRestoration = 'manual';

	 },
	 
	'public synch' : function() {
		var data = {};
		var that = this;
		
		if ( this.token != null ) 
			data['token'] = this.token;
		
		const w = DefaultInformationDialog().display('Loading...');
		const sy = window.scrollY;
		
		this
			.httpClient()
			.get( ActivityWall.$('HANDLER').DATA , data )
			.then( e => {
	        	 that.setToken(e.token);
	        	 e.data.data.forEach(function(o){ o.liked = o.likes.filter(e => e.uid == that.getUser().userId ).length  != 0 ;  });
	        	 e.data.data.forEach(function(o){  
	        		 if ( o.comments.length > 0 ) {
	        			 o.comments.forEach(function(e){
	        				 e.liked = e.likes.filter(e => e.uid == that.getUser().userId ).length  != 0 ;
	        			 });
	        		 }
	        	 });
	        	 
	        	 
	        	 that.getNode().find('ul.activity--items').last().append( 
	        			 Mustache.render( 
	        					 that.getTemplate() ,  {
	        						 dateFormat : function() {
	        							 return function(text,render) {
	        								 return moment(render(text)).format("YYYY-MM-DD HH:mm:ss" );
	        							 }
	        						 },
	        						 items:  e.data.data, 
	        					 } 
	        			 )
	        	 );
	        	 
	        	 that.setData(e);
	        	 that.updateListeners();
	        	 w.hide();
	        	 window.scrollTo(0,sy);
	        	 
			});
	},
	
    'public virtual reload' : function() {
			this.token = null;
			this.getNode().find('ul').find('li').remove();
			this.synch();
	 },
	 
	 
	'public onCommentSubmit' : function(sender, o) {
    	var w = DefaultInformationDialog().display(__tr('msg.loading'));
    	sender
    		.httpClient()
    		.post(ActivityCommentBox.$('HANDLER').ADD , { id : sender.getPostId(),  text : sender.getEditor().getText() } )
    		.then( e => {
    			w.hide()
    			sender.addComment(e.data)
    			o.updateListeners()
    		})
	 },
	 
	'public onCommentReplySubmit' : function(sender, o) {
		var w = DefaultInformationDialog().display(__tr('msg.loading'));
		
		sender
		.httpClient()
		.post(
				ActivityCommentBox.$('HANDLER').REPLY, 
				{ id : sender.getPostId(), routing: sender.getNode().attr('data-routing'), text : sender.getEditor().getText() } 
		 )
		.then( e => {
			w.hide()
			sender.addReply(e.data);
			o.updateListeners()
		})
		
	},
	 
	'public updateListeners' : function() {
		var that = this;
		
		this.getNode().find('ul').find('[data-name=comment]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var id = jQuery(this).attr('data-id');
				if ( jQuery(this).parent().parent().find("div[data-id="+id+"]").length == 0  ) {
					var commentBox = new ActivityCommentBox( id , jQuery(this));
					commentBox.onSubmitEvent(that.onCommentSubmit,that);
					new UIControllerExecutor(  commentBox ).execute();  
				}
			} );
		});
		
		this.getNode().find('ul').find('[data-name=reply]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var id = jQuery(this).attr('data-id');
				if ( jQuery(this).parent().parent().find("div[data-id="+id+"]").length == 0  ) {
					var commentBox = new ActivityCommentBox( id , jQuery(this));
					commentBox.onSubmitEvent(that.onCommentReplySubmit,that);
					new UIControllerExecutor(  commentBox ).execute();  
				}
			} );
		});
		
		this.getNode().find('ul').find('[data-name=like]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var o = jQuery(this);
			
				that
					.httpForm()
					.post(
							ActivityWall.$('HANDLER').LIKE,
							{ id : o.attr('data-id'), routing : o.attr('data-routing') }
					).then( e => {
						 const data = e.data;
						 o.attr('data-name','nolike');
						 o.removeClass('btn-primary');
						 o.parent().parent().find('span[class=like-count]').each(function(){jQuery(this).html( new Number( jQuery(this).html() + 1  ) ); });
						 that.updateListeners();
						 
					}) ;
					
			});
		});
		
		this.getNode().find('ul').find('[data-name=nolike]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var o = jQuery(this);
				that
					.httpForm()
					.post(
							ActivityWall.$('HANDLER').NOLIKE,
							{ id : jQuery(this).attr('data-id'), routing : jQuery(this).attr('data-routing') } 
					).then( e => {
						 const data = e.data;
						 o.attr('data-name','like');
						 o.addClass('btn-primary');
						 o.parent().parent().find('span[class=like-count]').each(function(){jQuery(this).html( new Number( jQuery(this).html() - 1  ) ); });
						that.updateListeners();
					}) ;
				
			});
		});
	 },
	 
	'public addItem' : function(item) {
		var element = Mustache.render( 
				   this.getTemplate() ,  {
						dateFormat : function() {
							return function(text,render) {
								return moment(render(text)).format("YYYY-MM-DD HH:mm:ss" );
							}
						},
						items:  [
							jQuery.extend( { totalLikeCount : 0 ,  liked : item.uid == this.getUser().userId } ,item )
						], 
					} 
		);
		
		this.getNode().find('ul').prepend(element)
		this.updateListeners();
	 },
	
	'public getTemplate' : function() {
		return this.itemTemplate;
	 },
	
	'public setToken' : function(v) {
		this.token = v;
	 },
	 
	'public setData' : function(v) {
		this.data = v;
	 },
	 
	'public getData' : function() {
		return this.data;
	 },
	 
	'public getUser' : function() {
		return this.user;
	 },
	 
	'public virtual override onScrollFire' : function() {
		 if ( this.data != null && this.data.token != null && this.data.count > 0) {
			 this.synch(); 
		 }
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.reload();
		window.scrollTo(0,0);

	 },



});