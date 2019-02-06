/**
 * @author Attila  Barna
 */

var CurrentUser = easejs.Class('CurrentUser').extend(Controller,{
	
	'public static info' : function() {
		var c = new Controller();
		return c.load( ApplicationScope.API.USER.INFO , {}  ).data;
	}
});
