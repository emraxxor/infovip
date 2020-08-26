/**
 *  @author attila
 */
var BaseWaterfall = easejs.Class('BaseWaterfall').extend(Controller,{

	'protected id' : null,
	
	'protected scroll' : null,
	
	'protected waterfall' : null,
	
    'override virtual __construct': function (id) {
    	this.__super();
    	this.id = id;
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
   
    'public setWaterfall' : function(v) {
    	this.waterfall = v;
    },
    
    'public virtual onCreationComplete' : function() {
    	this.waterfall.find('img').each(function(){
    		jQuery(this).animate({
    		      opacity: 1
    		 }, 1000);
    	});
    },
    
    'public virtual display' : function() {}

	
});