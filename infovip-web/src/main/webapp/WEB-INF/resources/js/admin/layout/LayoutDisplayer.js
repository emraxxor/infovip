/**
 *  @author attila
 */
var LayoutDisplayer = easejs.FinalClass('LayoutDisplayer').extend({
	
	'private layout' : null,
	
    '__construct': function (data) {
    	if ( easejs.Class.isA(BaseLayout, data) ) {
    		 this.layout = data;
    	} else {
    		throw new Error('Unsupported type!');
    	}
    },
    
    'public static create' : function(layout) {
    	var layoutDisplayer = new LayoutDisplayer(layout);
    	layoutDisplayer.execute();
    }, 

    'public execute' : function() {
    	this.layout.display();
    }
	
});

//# sourceURL=/resources/js/admin/layout/LayoutDisplayer.js