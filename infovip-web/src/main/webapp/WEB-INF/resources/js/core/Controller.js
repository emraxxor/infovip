/**
 * Created by attila on 1/3/17.
 */
var Controller = easejs.Class('Controller').implement(IController).extend({


    'virtual __construct': function () {

    },
    
    'public mode' : function(handlerURL,mode,data) {
    	return this.load(handlerURL,jQuery.extend({'mode':mode},data));
    },

    /**
     * Async operation
     */
    'public async' : function(purl,pdata, callback, params ,methodType, ajaxGlobal, options) {
        var data = {
                type: methodType == undefined ? "POST" : methodType,
                url: purl,
                data: pdata,
                dataType: "html",
                global: ajaxGlobal == undefined ? true : ajaxGlobal,
                cache: false,
                async: true,
                error: function (request, status, error) {
                    if (request.responseText != null) {
                        callback(request,{ error: [request,status,error] });
                    }
                },
                success: function (data, textStatus, jqXHR) {
                    callback( jQuery.parseJSON(data) , params );
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
    'public load': function (purl, pdata, methodType, ajaxGlobal,async,options) {
        var rdata = null;
        var data = {
            type: methodType == undefined ? "POST" : methodType,
            url: purl,
            data: pdata,
            dataType: "html",
            global: ajaxGlobal == undefined ? true : ajaxGlobal,
            cache: false,
            async: async == undefined ? false : async,
            error: function (request, status, error) {
                if (request.responseText != null) {
                    // @todo handle error
                }
            },
            success: function (data, textStatus, jqXHR) {
                rdata = jQuery.parseJSON(data);
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
                    console.log(request.responseText);
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
    }

});