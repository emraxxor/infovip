/**
 * Created by attila on 1/3/17.
 * It is similar to ScriptLoader but 
 */
var DependencyLoader = easejs.FinalClass('DependencyLoader',{

    /**
     * Stores the dependencies
     */
    'private dependencies' : [],

    /**
     * Stores what kind of components are already loaded
     */
    'public static loadedComponents' : [],
    

    /**
     * Creates a new instance
     */
    'public static create' : function () {
        return new DependencyLoader();
    },

    /**
     * Appends a new js file
     */
    'public js' : function (url) {
        this.dependencies.push({type:"javascript",url:url});
        return this;
    },

    /**
     * Gets the length of the dependencies
     */
    'public length' : function() {
        return this.dependencies.length;
     },

     /***
      * Appends a new css file
      */
    'public css' : function(url) {
    	 this.dependencies.push({type:"css",url:url});
         return this;
     },
     
     
    /**
     * Gets the dependency by the specified index
     * @param i
     * @returns {*}
     */
    'public getDependency' : function(i) {
        return this.dependencies[i];
    },
    

    'public import' : function(callback,param) {
        var _self = this;
        
        jQuery(document).ready(function() {
        	for (var i = 0; i < _self.length(); i++) {            	
            	var dependency = _self.getDependency(i) ;
            	if ( dependency.type == 'javascript' ) {
            	
            		if ( DependencyLoader.$('loadedComponents').indexOf(dependency.url) != -1 )
            			return;
            		
            		DependencyLoader.$('loadedComponents').push(dependency.url);
            		
					jQuery.ajax({
						url: ApplicationScope.config.RESOURCES_PATH + '/' + dependency.url + '.js',
						dataType: "script",
						async: false,
						success: function () {
						},
						error: function (jqXHR, textStatus, errorThrown) {
							console.error(jqXHR);
							console.error(textStatus);
							console.error(errorThrown);
							throw new Error("Following element could not be loaded " + dependency.url);
						}
					});
            	} else if ( dependency.type == 'css' ) {
            		jQuery("<link/>", { rel: "stylesheet", type: "text/css", href: ApplicationScope.config.RESOURCES_PATH + '/' + dependency.url + '.css' }).appendTo("head");
            	}
            }
        	
        	if ( callback != undefined )
        		callback(param);
        });
        return this;
    }
});
