/**
 *  @author Attila Barna
 */
var MediaPageWall = easejs.Class('MediaPageWall').extend(BaseWaterfall,{

	
	'public static TEMPLATE_MST' : ApplicationScope.config.RESOURCES_PATH + '/mst/media/MediaBox.mst',

	'private template' : null,
	
    'override __construct': function (id) {
    	this.__super(id);
    	this.template = this.load( MediaPageWall.$('TEMPLATE_MST') , {}, 'GET'  );

     },

    'public override onCreationComplete' : function() {
     },
    
    'public reloadWaterfall' : function() {
    	this.setScroll(null);
    	this.waterfall.waterfall('reload');
     },
    
    'public getTemplate' : function() {
    	return this.template;
     },
     
    'public uid' : function() {
    	return self.location.pathname.split('/')[2];
    }, 

	'public override display' : function() {
    	const that = this;
    	const waterfall = jQuery('#' + this.id).waterfall({
    		itemCls: 'item-waterfall', 
    		prefix: 'waterfall',
    		fitWidth: true, 
    		colWidth: 180, 
    		gutterWidth: 10,
    		gutterHeight: 10,
    		align: 'center',
    		minCol: 1, 
    		maxCol: undefined, 
    		maxPage: undefined, 
    		//bufferPixel: -50, 
    		containerStyle: {
    			position: 'relative'
    		},
    		resizable: true, 
    		isFadeIn: true,
    		isAnimated: true,
    		animationOptions: { 
    		},
    		isAutoPrefill: true,
    		checkImagesLoaded: true,
    		dataType: 'json', 
    		path: function(page) {
    			return '/user/'+ that.uid() +'/media/data/?token=' + that.getScroll() ;
    		},
    		params: {},     		
    		loadingMsg: '<div style="text-align:center;padding:10px 0; color:#999;"><img src="data:image/gif;base64,R0lGODlhEAALAPQAAP///zMzM+Li4tra2u7u7jk5OTMzM1hYWJubm4CAgMjIyE9PT29vb6KiooODg8vLy1JSUjc3N3Jycuvr6+Dg4Pb29mBgYOPj4/X19cXFxbOzs9XV1fHx8TMzMzMzMzMzMyH5BAkLAAAAIf4aQ3JlYXRlZCB3aXRoIGFqYXhsb2FkLmluZm8AIf8LTkVUU0NBUEUyLjADAQAAACwAAAAAEAALAAAFLSAgjmRpnqSgCuLKAq5AEIM4zDVw03ve27ifDgfkEYe04kDIDC5zrtYKRa2WQgAh+QQJCwAAACwAAAAAEAALAAAFJGBhGAVgnqhpHIeRvsDawqns0qeN5+y967tYLyicBYE7EYkYAgAh+QQJCwAAACwAAAAAEAALAAAFNiAgjothLOOIJAkiGgxjpGKiKMkbz7SN6zIawJcDwIK9W/HISxGBzdHTuBNOmcJVCyoUlk7CEAAh+QQJCwAAACwAAAAAEAALAAAFNSAgjqQIRRFUAo3jNGIkSdHqPI8Tz3V55zuaDacDyIQ+YrBH+hWPzJFzOQQaeavWi7oqnVIhACH5BAkLAAAALAAAAAAQAAsAAAUyICCOZGme1rJY5kRRk7hI0mJSVUXJtF3iOl7tltsBZsNfUegjAY3I5sgFY55KqdX1GgIAIfkECQsAAAAsAAAAABAACwAABTcgII5kaZ4kcV2EqLJipmnZhWGXaOOitm2aXQ4g7P2Ct2ER4AMul00kj5g0Al8tADY2y6C+4FIIACH5BAkLAAAALAAAAAAQAAsAAAUvICCOZGme5ERRk6iy7qpyHCVStA3gNa/7txxwlwv2isSacYUc+l4tADQGQ1mvpBAAIfkECQsAAAAsAAAAABAACwAABS8gII5kaZ7kRFGTqLLuqnIcJVK0DeA1r/u3HHCXC/aKxJpxhRz6Xi0ANAZDWa+kEAA7" alt=""><br />Loading...</div>',
    		state: { 
    			isDuringAjax: false, 
    			isProcessingData: false, 
    			isResizing: true,
    			curPage: 1 
    		},
    		callbacks: {
    		
    			/*
    			 * loadingStart
    			 * @param {Object} loading $('#waterfall-loading')
    			 */
    			loadingStart: function(loading) {
    				loading.show();
    			},
    			
    			/*
    			 * loadingFinished
    			 * @param {Object} loading $('#waterfall-loading')
    			 * @param {Boolean} isBeyondMaxPage
    			 */
    			loadingFinished: function(loading, isBeyondMaxPage) {
    				if ( !isBeyondMaxPage ) {
    					loading.fadeOut();
    					that.onCreationComplete();
    				} else {
    					loading.remove();
    				}
    			},
    			
    			/*
    			 * loadingError
    			 * @param {String} xhr , "end" "error"
    			 */
    			loadingError: function(message, xhr) {
    				message.html('Data load faild, please try again later.');
    			},
    			
    			/*
    			 * renderData
    			 * @param {String} data
    			 * @param {String} dataType , "json", "jsonp", "html"
    			 */
    			renderData: function (data, dataType) {
    				var template, result;
    				
    				result = new Array();
    				
    				if ( dataType === 'json' ) { // json or jsonp format
    					if ( data.data.length < 50 ) {
    						that.waterfall.waterfall('pause', function() {
    							jQuery('#waterfall-message').html('<p style="color:#666;">no more data...</p>')
    						});
    					}
    		            
    					for(var i=0; i<data.data.length; i++) {
    						let o = data.data[i];
    						let image = new String( WEB_DIR + "/user/media-image?noimage");
    						if ( o.photo != undefined ) {
    							image = new String( WEB_DIR + "/user/media-image?" + o.photo.data);
    						}
    						result.push( {image : image, data: o , uid : that.uid() } );
    					}
    					
    					that.setScroll(data.token);
    					template = Handlebars.compile(that.template);
    					return template({total:data.total, result: result});
    				} else { 
		                that.getWaterfall().waterfall('pause', function() {
		                    jQuery('#waterfall-message').html('<p style="color:#666;">no more data...</p>')
		                });
    					return "";
    				}
    			}
    		},
    		
    		debug: false
    	});

    	this.setWaterfall(waterfall);
	}
	
});