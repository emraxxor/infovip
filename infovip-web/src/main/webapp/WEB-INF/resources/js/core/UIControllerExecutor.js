/**
 *  @author Attila Barna
 */
var UIControllerExecutor = easejs.Class('UIControllerExecutor').extend({
	
	'private uiComponent' : null,
	
	'virtual __construct' : function(component) {
        if (easejs.Class.isA(UIController, component)) {
        	this.uiComponent = component;
        }
	},
	
	'public virtual execute' : function() {
		if ( this.uiComponent != null ) {
			this.uiComponent.postConstruct();
			this.uiComponent.render();
			this.uiComponent.onCreationComplete();
			this.uiComponent.preDestroy();
			this.uiComponent.destroy();
		}
	}
});
