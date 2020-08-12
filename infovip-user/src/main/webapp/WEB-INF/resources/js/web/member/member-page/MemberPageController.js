/**
 * 
 * @author Attila Barna
 */
var MemberPageController = easejs.Class('MemberPageController').extend(UIController,{

	'private memberPageWall' : null,
	
    'override virtual __construct' : function() {
    	this.memberPageWall = new MemberPageWall('membersResult');
     },

	'public override virtual events' : function() {
	 },
	 	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
		this.memberPageWall.display();
	 }

});
