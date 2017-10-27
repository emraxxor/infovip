/**
 * A simple form manager wrapper 
 * 
 * @author attila
 */
var FormManager = easejs.Class('FormManager').extend({

	'private form' : null,
	
	'virtual __construct' : function(form) {
    	if ( easejs.Class.isA(BaseForm, form) ) {
    		this.form = form; 
    	} else {
    		throw new Error("Unsupported form!");
    	}
	},

	/**
	 * It creates a new view that is suitable for the current framework.
	 * The view can be used for any webix component.
	 * 
	 * @param view BaseView
	 * Expects an Object that is an instance of BaseView.
	 * 
	 * @return webix.object
	 * 
	 * @author attila
	 */
	'public static createView' : function(view) {
		if ( easejs.Class.isA(BaseView,view) ) {
			view.postInitialization();
			return view;
		} else {
			throw new Error("Unsupported type!");
		}
	},
	
	'public static display' : function(form) {
		FormManager(form).execute();
	},
	
	'public execute' : function() {
		this.form.onBeforeInit();
		this.form.display();
	}
});
