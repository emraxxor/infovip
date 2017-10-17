/**
 * @author attila 
 */
var DefaultNavBar = easejs.Class('DefaultNavBar').extend(BaseNavBar,{
	
    'override virtual __construct': function (waterfall) {
    	this.__super(waterfall);
    	this.setId("defaultSearchUniNavbar");
    }
});
