/**
 * @author Attila Barna
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
		this.itemTemplate = this.load( ActivityWall.$('TEMPLATE').ITEM , {}, 'GET'  );
		this.user = CurrentUser.info();
		if ('scrollRestoration' in history) {
			  history.scrollRestoration = 'manual';
		}

	 },
	 
	'public synch' : function() {
		var data = {};
		var that = this;
		
		if ( this.token != null ) 
			data['token'] = this.token;
		
		const w = DefaultInformationDialog().display('Loading...');
		const sy = window.scrollY;
        this.async( ActivityWall.$('HANDLER').DATA, data, function(data,o) {
        	that.setToken(data.token);
        	data.data.forEach(function(o){ o.liked = o.likes.filter(e => e.uid == that.getUser().userId ).length  != 0 ;  });
        	data.data.forEach(function(o){  
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
    							items:  data.data, 
    						} 
    				)
    		);

    		that.setData(data);
    	    that.updateListeners();
    	    w.hide();
    	    window.scrollTo(0,sy);
        }, this );
	},
	
    'public virtual reload' : function() {
			this.token = null;
			this.getNode().find('ul').find('li').remove();
			this.synch();
	 },
	 
	 
	'public onCommentSubmit' : function(sender, o) {
    	var w = DefaultInformationDialog().display(__tr('msg.loading'));
		sender.async( ActivityCommentBox.$('HANDLER').ADD , { id : sender.getPostId(),  text : sender.getEditor().getText() } , function( data , sender ) {
				w.hide();
				sender.addComment(data);
				o.updateListeners();
		} , sender );
	 },
	 
	'public onCommentReplySubmit' : function(sender, o) {
		var w = DefaultInformationDialog().display(__tr('msg.loading'));
		sender.async( ActivityCommentBox.$('HANDLER').REPLY , { id : sender.getPostId(), routing: sender.getNode().attr('data-routing'), text : sender.getEditor().getText() } , function( data , sender ) {
				w.hide();
				sender.addReply(data);
				o.updateListeners();
		} , sender );
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
				that.async( ActivityWall.$('HANDLER').LIKE , { id : o.attr('data-id'), routing : o.attr('data-routing') } , function(data,ob) {
					o.attr('data-name','nolike');
					o.removeClass('btn-primary');
					o.parent().parent().find('span[class=like-count]').each(function(){jQuery(this).html( new Number( jQuery(this).html() + 1  ) ); });
					ob.updateListeners();
				} , that  );
			});
		});
		
		this.getNode().find('ul').find('[data-name=nolike]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var o = jQuery(this);
				that.async( ActivityWall.$('HANDLER').NOLIKE , { id : jQuery(this).attr('data-id'), routing : jQuery(this).attr('data-routing') } , function(data,ob) {
					if ( data.result == "DELETED" ) {
						o.attr('data-name','like');
						o.addClass('btn-primary');
						o.parent().parent().find('span[class=like-count]').each(function(){jQuery(this).html( new Number( jQuery(this).html() - 1  ) ); });
						ob.updateListeners();
					}
				} , that  );
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