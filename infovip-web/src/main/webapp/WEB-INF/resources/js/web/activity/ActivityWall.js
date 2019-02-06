/**
 * @author Attila Barna
 */
var ActivityWall = easejs.Class('ActivityWall').extend(IScroll,{

	'public static HANDLER' : {
		DATA : WEB_DIR + "/activity/data"
	 },

	'public static TEMPLATE' : {
		ITEM : ApplicationScope.config.RESOURCES_PATH + '/js/web/activity/ActivityItem.mst'
	 },

	'private itemTemplate' : null,
	
	'private token' : null,
	
	'private data' : null,
	
	'private size' : 50,
	
	'private user' : null,
	
	'override virtual __construct' : function(node) {
		this.__super(node);
		this.itemTemplate = this.load( ActivityWall.$('TEMPLATE').ITEM , {}, 'GET'  );
		this.user = CurrentUser.info();
	 },
	 
	'public synch' : function() {
		var data = {};
		var that = this;
		
		if ( this.token != null ) 
			data['token'] = this.token;
		
		
        this.async( ActivityWall.$('HANDLER').DATA, data, function(data,o) {
        	that.setToken(data.token);
    		that.getNode().find('ul').append( 
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
    		
    	    that.updateListeners();
        }, this );
	},
	
    'public virtual reload' : function() {
			this.token = null;
			this.getNode().find('ul').find('li').remove();
			this.synch();
	 },
	 
	'public updateListeners' : function() {
		this.getNode().find('ul').find('[data-name=comment]').each(function(){
			jQuery(this).off('click').on('click', function(e) {
				var id = jQuery(this).attr('data-id');
				if ( jQuery(this).parent().parent().find("div[data-id="+id+"]").length == 0  ) {
					var commentBox = new ActivityCommentBox( id , jQuery(this));
					new UIControllerExecutor(  commentBox ).execute();  
				}
			} );
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
							item
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
	 
	'public virtual override onScrollFire' : function() {
		 if ( this.data != null && this.data.count >= this.size ) {
			this.synch(); 
		 }
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.reload();
	 },



});