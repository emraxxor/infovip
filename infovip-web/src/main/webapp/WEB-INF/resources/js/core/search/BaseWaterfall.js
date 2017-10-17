/**
 *  @author attila
 */
var BaseWaterfall = easejs.Class('BaseWaterfall').extend(Controller,{

	'private id' : null,
	
	'private scroll' : null,
	
	'public static WATERFALL_SERVLET' : 'search/result',
	
	'private waterfall' : null,
	
	'private term' : null,
	
    'override virtual __construct': function () {
    	this.__super();
    },
    
    'public setId' : function(val) {
    	this.id = val;
    },
    
    'public getId' : function() {
    	return this.id;
    },
    
    'public setScroll' : function(val) {
    	this.scroll = val;
    },
    
    'public setTerm' : function(val) {
    	this.scroll = null;
    	this.term = val;
    },
    
    'public getScroll' : function() {
    	return this.scroll;
    },
    
    'public componentToHex' : function(c) {
        var hex = c.toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    },

    'public hexToRgb' : function(hex) {
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    },
    
    'public rgbToHex' : function(r, g, b) {
        return "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    },
    
    'public getWaterfall' : function() {
    	return this.waterfall;
    },
    
    'public onCreationComplete' : function() {
    	this.waterfall.find('img').each(function(){
    		jQuery(this).animate({
    		      opacity: 1
    		 }, 1000);
    	});
    },
    
    'public getTerm' : function() {
    	return this.term;
    },
    
    'public virtual display' : function() {
    	var that = this;
    	this.waterfall = jQuery('#' + this.id).waterfall({
    		itemCls: 'item', 
    		prefix: 'waterfall',
    		fitWidth: true, 
    		colWidth: 204, 
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
    		//isAutoPrefill: true,
    		checkImagesLoaded: false,
    		dataType: 'json', 
    		path: function(page) {
    			return BaseWaterfall.$('WATERFALL_SERVLET') + '?page=' + page + '&scroll=' + that.getScroll() + '&term=' + that.getTerm();
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
    				var tpl, template, result;
    				
    				result = new Array();
    				
    				if ( dataType === 'json' ) { // json or jsonp format

    					if ( data.data.length < 100) {
    						that.getWaterfall().waterfall('pause', function() {
    							jQuery('#waterfall-message').html('<p style="color:#666;">no more data...</p>')
    						});
    					}
    		            
    					for(var i=0; i<data.data.length; i++) {
    						var o = data.data[i];
    						var dc = o.dominantColor;
    						var image = new String( WEB_DIR + "/ProductImage?180_" + o.imageName + '.' + o.imageType);
    						var bgc = that.rgbToHex(dc[0],dc[1],dc[2]);
    						result.push( {image : image, width : o.width , color: bgc , height: o.height} );
    					}
    					
    					that.setScroll(data.scroll);
    					
    					tpl = jQuery('#waterfall-tpl').html();
    					template = Handlebars.compile(tpl);
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
    }

	
});