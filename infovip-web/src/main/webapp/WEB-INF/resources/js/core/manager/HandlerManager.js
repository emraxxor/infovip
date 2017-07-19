/**
 *  @author attila
 */
var HandlerManager = easejs.FinalClass('HandlerManager').extend({
	
	/**
	 * @type {DefaultHandlerAdapter} handlerAdapter
	 */
	'private handlerAdapter' : null,
	
    '__construct': function (adapter) {
    	if ( easejs.Class.isA(DefaultHandlerAdapter, adapter) ) {
    		 this.handlerAdapter = adapter;
    	} else {
    		throw new Error('Unsupported type!');
    	}
    },

    'public execute' : function() {
    	this.handlerAdapter.postConstruct();
    	this.handlerAdapter.onCreationComplete();
    	this.handlerAdapter.preDestroy();
    }
	
});
