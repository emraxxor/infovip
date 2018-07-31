var WindowDisplayer = easejs.Class('WindowDisplayer').extend({

	'private window' : null,
	
	'virtual __construct' : function(w,p) {
    	if ( easejs.Class.isA(BaseDialogWindow) )  {
    		this.window = w;
    		this.window.setParent(p);
    		this.window.postConstruct();
    	} else if ( easejs.Class.isA(BaseWindow, w) &&  !easejs.Class.isA(BaseDialogWindow) ) {
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
