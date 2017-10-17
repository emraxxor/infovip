/**
 * @author attila 
 */
var DefaultWaterfall = easejs.Class('DefaultWaterfall').extend(BaseWaterfall,{
	
    'override virtual __construct': function () {
    	this.__super();
    	this.setId("mainSearchResult");
    }
});
