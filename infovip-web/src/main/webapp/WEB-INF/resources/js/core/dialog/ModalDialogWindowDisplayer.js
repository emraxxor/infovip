/**
 * @author attila
 */
var ModalDialogWindowDisplayer = easejs.Class('ModalDialogWindowDisplayer').extend(Controller,{
	
	 'private window' : null,
	
	 'override __construct' : function (window) {
	        this.__super();
	        this.window = window;
	  },
	  
	  'public static create' : function(window) {
		  return new ModalDialogWindowDisplayer(window);
	  },
	  
	  'public display' : function() {
		  this.window.beforeCreationComplete();
		  this.window.initComponents();
		  this.window.create();
		  this.window.display();
	  }

});

//@ sourceURL=/js/core/ModalDialogWindowDisplayer.js
