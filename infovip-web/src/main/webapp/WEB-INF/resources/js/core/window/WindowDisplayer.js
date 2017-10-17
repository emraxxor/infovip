var WindowDisplayer = easejs.Class('WindowDisplayer').extend({

	'private window' : null,
	
	'virtual __construct' : function(w) {
    	if ( easejs.Class.isA(BaseWindow, w) ) {
    		this.window = w; 
    	} else {
    		throw new Error("Unsupported form!");
    	}
	},
	
	'public static display' : function(w) {
		WindowDisplayer(w).execute();
	},
	
	'public execute' : function() {
		this.window.beforeDisplay();
		this.window.create();
		this.window.views();
		this.window.display();
	}
});
