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
    }

});