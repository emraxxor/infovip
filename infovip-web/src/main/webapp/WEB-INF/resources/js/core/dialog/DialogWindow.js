/**
 * 
 */
var DialogWindow = easejs.Class('DialogWindow').extend(Controller,{

	'protected dialogID' : null,
	
	 /**
     * The text for the current window
     */
    'protected text' : '',

	
	
    'public static inputName' : function(inputName) {
    	return "input[name='"+ inputName+"']";
    },

	
    'override virtual __construct' : function (id) {
        this.__super();
        this.dialogID = id;
    },

    
    /**
     * Sets the text of the current window
     */
    'public virtual setText' : function(text) {
        this.text = text;
     },
     
    'public virtual display' : function() {
    	 throw new Error("Not implemented yet!");
     },
     
     /**
      * Gets the text of the current window
      * @returns {*}
      */
     'public getText' : function () {
         return this.text;
     },
});
