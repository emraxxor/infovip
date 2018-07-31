/**
 *  Using the StatusDisplayer is recommended if you want to display a message, regardless of the value of output, 
 *  if no further action is required.
 *  
 *  @author Attila Barna
 */
var StatusDisplayer = easejs.Class('StatusDisplayer').extend({
	
	'private statusObject' : null,
	
	'virtual __construct' : function(object) {
		this.statusObject = object;
	},

	'display' : function() {
		if ( this.statusObject.code == ApplicationScope.response.Code.ERROR ) {
			   DefaultErrorHandler(this.statusObject).display();
		} else {
			   DefaultStatusHandler(this.statusObject).display();
		}
	}
	
});
