/**
 *  @author Attila Barna
 */
var ModuleManager = easejs.FinalClass('ModuleManager',{
	
	/**
	 * @type List<Module>
	 */
	'private modules' : null,
	

    'virtual __construct': function () {
    	this.modules = new Array();
    },
    
    'public load' : function(
    		url , 
    		path = ApplicationScope.config.RESOURCES_PATH  
    	) { 
    	jQuery.ajax({url: path + '/' + url + '.js',dataType: "script", async: false});
    	return this;
    },
    
	'public addModule' : function(o) { 
		if (easejs.Class.isA(Module, o)) {
        	this.modules.push(o);
        } else {
        	throw new Error("Unsupported element");
        }  
	},
	
	'public add' : function(m) {
		this.addModule(m);
	},
	
	'public removeModule' : function(v) {
        this.modules = this.modules.filter(o=> o !== v);
	},
	
	'public readyStateChange' : function() {
		this.modules.forEach(o => o.load());
	}
	
	
});

var $ModuleManager = ModuleManager();
jQuery(document).ready(() => $ModuleManager.readyStateChange());
