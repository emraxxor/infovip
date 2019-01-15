/**
 * Created by attila on 1/3/17.
 */
var Controller = easejs.Class('Controller').implement(IController).extend({

	'private options' : {},

    'virtual __construct': function () {
    	
    },
    
    
    'public addOption' : function(name,val) {
    	this.options[name] = val;
    },
    
    'public getOption' : function(name) {
    	return this.options[name];
    },
    
    'public existsOption' : function(name) {
    	return this.options[name] != undefined;
    },
    
    'public removeOption' : function(name) {
    	if ( this.existsOption(name) ) {
    		this.options[name] = null;
    	}
    },
    
    'public mode' : function(handlerURL,mode,data) {
    	return this.load(handlerURL,jQuery.extend({'mode':mode},data));
    },

    /**
     * Async operation
     */
    'public async' : function(purl,pdata, callback, params ,methodType, ajaxGlobal, dataType, options, contentType) {
    	var data = {
                type: methodType == undefined ? "POST" : methodType,
                url: purl,
                data: pdata,
                contentType: contentType == undefined  ? "application/x-www-form-urlencoded; charset=UTF-8" : contentType + ";charset=UTF-8",
                dataType: dataType == undefined  ? "html" : dataType,
                global: ajaxGlobal == undefined ? true : ajaxGlobal,
                cache: false,
                async: true,
                error: function (request, status, error) {
                    if (request.responseText != null) {
                        callback(request,{ error: [request,status,error] });
                    }
                },
                success: function (data, textStatus, jqXHR) {
                    if ( callback != undefined ) {
                        try {
                        	rdata = JSON.parse(data);
                        } catch (e) {
                        	rdata = data;
                        }
                        callback(rdata , params );
                    }
                	
                }
        };
        
        if ( options != undefined ) {
        	data = jQuery.extend(data,options);
        }

        jQuery.ajax(data);
    },
    
    /**
     *
     * @param purl
     * @param pdata
     * @param methodType
     * @param ajaxGlobal
     * @returns {*}
     */
    'public load': function (purl, pdata, methodType, ajaxGlobal,async,dataType,options,contentType) {
        var rdata = null;
        var data = {
            type: methodType == undefined ? "POST" : methodType,
            url: purl,
            data: pdata,
            contentType: contentType == undefined  ? "application/x-www-form-urlencoded; charset=UTF-8" : contentType + ";charset=UTF-8",
            dataType: dataType == undefined  ? "html" : dataType,
            global: ajaxGlobal == undefined ? true : ajaxGlobal,
            cache: false,
            async: async == undefined ? false : async,
            error: function (request, status, error) {
                if (request.responseText != null) {
                    // @todo handle error
                }
            },
            success: function (data, textStatus, jqXHR) {
                try {
                    rdata = JSON.parse(data);
                } catch (e) {
                    rdata = data;
                }
            }
        };
        
        if ( options != undefined ) {
        	data = jQuery.extend(data,options);
        }
        
        jQuery.ajax(data);
        
        return rdata;
    },

    /**
     *
     * @param purl
     * @param pdata
     */
    'public execute': function (purl, pdata,async) {
        jQuery.ajax({
            type: "POST",
            url: purl,
            data: pdata,
            dataType: "html",
            cache: false,
            async: async == undefined ? false : async,
            error: function (request, status, error) {
                if (request.responseText != null) {
                    // @todo handle error
                }
            },
            success: function (data, textStatus, jqXHR) {
            }
        });
    },
    
    /**
     * Gets the data items from an ui.view data component
     * @param {type} obj
     * @returns {undefined}
     */
    'public WIXDataItems' : function (obj) {
        var data = [];
        obj.data.each(function (obj) {
            data.push(obj);
        });
        return data;
    },
    
    /**
     * Checks if the given #val exists in the #items by #primaryID
     * @param {type} items
     * @param {type} primaryID
     * @param {type} val
     * @returns {Boolean}
     */
    'public WIXExistsItem' : function (items, primaryID, val) {
        for (var i = 0; i < items.length; i++) {
            if (items[i][primaryID] == val) {
                return true;
            }
        }
        return false;
    },
    
    /**
     * Adds items to the given ui.view data object
     * @param {type} items
     * @param {type} toObj
     * @returns {undefined}
     */
    'public WIXAddItemsTo' : function (items, toObj, primaryID) {
        var prID = primaryID == undefined ? "id" : primaryID;
        for (var i = 0; i < items.length; i++) {
            if (!this.WIXExistsItem(this.WIXDataItems(toObj), prID, items[i][prID])) {
                toObj.add(items[i]);
            }
        }
    },

    /**
     * Copy context's items
     * @param {type} context
     * @returns {undefined}
     */
    'public WIXCopyItems' : function (context) {
        for (var i = 0; i < context.source.length; i++) {
            context.from.copy(context.source[i], context.start, this, webix.uid());
        }
    },
    
    
    'public sleep' : function(ms) {
  	  return new Promise(resolve => setTimeout(resolve, ms));
    },


});