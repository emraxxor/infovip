/* 
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * The location of the current web application
 * It is really recommended  to use this constant instaed of document.location
 * 
 * @type String
 */
var WEB_DIR = "/infovip-web";

/**
 * Initializations of libraries 
 */

// HIGHSLIDE
hs.graphicsDir = WEB_DIR + '/resources/lib/highslide/highslide/graphics/';
hs.align = 'center';
hs.transitions = ['expand', 'crossfade'];
hs.outlineType = 'rounded-white';
hs.fadeInOut = true;
hs.dimmingOpacity = 0.75;
hs.zIndexCounter = 10031;

if (hs.registerOverlay) { 
	hs.registerOverlay(
			{ 
				thumbnailId: 'productImage', 
				html: '<div class="closebutton" onclick="return hs.close(this)" title="Close"></div>', 
				position: 'top right', 
				fade: 2  
			}
	);  
}

// Add the controlbar
hs.addSlideshow({
    interval: 5000,
    repeat: false,
    useControls: true,
    fixedControls: 'fit',
    overlayOptions: {
        opacity: .75,
        position: 'bottom center',
        hideOnMouseOut: true
    }
});

// DOJO
var dojoConfig = {
    has: {
        "dojo-firebug": true
    },
    baseUrl: WEB_DIR + '/resources/',
    parseOnLoad: false,
    async: false,
    packages: [
        {name: "dojo", location: "lib/dojo/dojo"},
        {name: "dijit", location: "lib/dojo/dijit"},
        {name: "dojox", location: "lib/dojo/dojox"}
    ]
};


// Other resources

/**
 * Creates a format method if it doesn't exists
 * 
 */
if (!String.prototype.format) {

    /**
     * Formats the message
     * @example 
     * "string {0} {1}", var1, var2
     * 
     * @returns {String.prototype@call;replace}
     */
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}

/**
 * Conflict with easejs
 * @type Object
 */
var $jquery = jQuery.noConflict();


/**
 * Override the default search function
 */
jQuery.fn.dataTableExt.oApi.fnFilterOnReturn = function (oSettings) {
	var _that = this;
	
	this.each(function (i) {
	    $.fn.dataTableExt.iApiIndex = i;
	    var $this = this;
	    var anControl = $('input', _that.fnSettings().aanFeatures.f);
	    anControl
	        .unbind('keyup search input')
	        .bind('keypress', function (e) {
	            if (e.which == 13) {
	                $.fn.dataTableExt.iApiIndex = i;
	                _that.fnFilter(anControl.val());
	            }
	        });
	    return this;
	});
	return this;
}




/**
 * Configuration directives
 */
var ApplicationScope = {
		config : {
			RESOURCES_PATH : WEB_DIR + '/resources',
			JS_PATH : WEB_DIR + '/resources/js',
			WEB_ROOT : WEB_DIR
		},
		response : {
			Code : {
				ERROR : -1,
				SUCCESS : 1
			}
		}

};
