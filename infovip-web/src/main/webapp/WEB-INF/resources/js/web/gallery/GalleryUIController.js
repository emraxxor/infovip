/**
 * 
 * @author Attila Barna
 */
var GalleryUIController = easejs.Class('GalleryUIController').extend(UIController,{
	
	'private galleryWall' : null,
	
	'private scroll' : null,
	
	'private waterfall' : null,

	
	'override virtual __construct' : function() {
		 this.galleryWall = jQuery("div[id=gallery-data]");
	},
	
    'public getWaterfall' : function() {
    	return this.waterfall;
    },
    
	
    'public override virtual onCreationComplete' : function() {    	
    	this.waterfall.find('img').each(function(){ /**jQuery(this).animate({opacity: 1}, 1000);**/ });
    	this.updateRenderedData();
	},
    
   'public override virtual render' : function() {
   	var that = this;
	this.waterfall = jQuery('#' + this.id).waterfall({
		itemCls: 'item-waterfall', 
		prefix: 'waterfall',
		fitWidth: true, 
		colWidth: 254, 
		gutterWidth: 10,
		gutterHeight: 10,
		align: 'center',
		minCol: 1, 
		maxCol: undefined, 
		maxPage: undefined, 
		bufferPixel: -50, 
		containerStyle: {
			position: 'relative'
		},
		resizable: true, 
		isFadeIn: true,
		isAnimated: true,
		animationOptions: { },
		//isAutoPrefill: true,
		checkImagesLoaded: false,
		dataType: 'json', 
		path: function(page) {
			return 'FETCH_URL';
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
			loadingFinished: function(loading, isBeyondMaxPage,xhr) {
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
				message.html('Data load faild, please try again.');
			},
			
			/*
			 * renderData
			 * @param {String} data
			 * @param {String} dataType , "json", "jsonp", "html"
			 */
			renderData: function (data, dataType) {
				var tpl, template, result;
				
				result = new Array();
				
				if ( dataType === 'json' ) { // json or jsonp format

					if ( data.data.length == 0) {
						that.getWaterfall().waterfall('pause', function() {
							jQuery('#waterfall-message').html('<p style="color:#666;">'+__tr('no.more.data')+'</p>')
						});
						
						return "";
					}
					
					for(var i=0; i<data.data.length; i++) {
						var po = data.data[i];
						
						if ( data.data[i].imageData.length == 0 )
							continue;
						
						var o = data.data[i].imageData[0];
						var b = data.data[i];
						var dc = o.dominantColor;
						var image = new String( WEB_DIR + "/gallery/image?" + o.imageName + '.' + o.imageType);
						var bgc = that.rgbToHex(dc[0],dc[1],dc[2]);
						
						result.push( { 
							edata: Base64.encode(JSON.stringify(po)), 
							highlighted: (Math.floor(Math.random() * 100) > 50),  
							image : image, 
							documentId: b.documentId , 
							width : o.width , 
							color: bgc , 
							height: o.height, 
							data:po, 
						});
					}
					
					that.setScroll(data.scroll);
					tpl = jQuery('#gallery-template').html();
					template = Handlebars.compile(tpl);
					return template({total:data.total, result: result});
				} else { 
	                that.getWaterfall().waterfall('pause', function() {
	                    jQuery('#waterfall-message').html('<p style="color:#666;">'+__tr('no.more.data')+'</p>')
	                });
					return "";
				}
			}
		},
		
		debug: false
	});
   },
   
    
    /**
     * By default, it updates the event listeners of the elements that are already displayed in the Panel.
     */
    'public virtual updateRenderedData' : function() {
    	var that = this;
    	this.waterfall.find('div[event=unset]').each(function(){
    		var e = jQuery(this);
    		var data = jQuery.parseJSON( Base64.decode(e.attr('edata')) );
    		var p = e.find('.port-item-product');
    		
    		e.attr('event','set');
    		
    		p.on('click',function(ev) {});
    		
    		e.on('mouseover',function(){e.addClass('item-event-hover');});
    		
    		e.on('mouseout',function(){e.removeClass('item-event-hover');});
    		
    	});
    },

});
