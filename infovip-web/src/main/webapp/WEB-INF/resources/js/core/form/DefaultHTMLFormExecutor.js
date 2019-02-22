/**
 *  @author Attila Barna
 */
var DefaultHTMLFormExecutor = easejs.Class('DefaultHTMLForm').extend({
	
	'private uiComponent' : null,
	
	'virtual __construct' : function(component) {
        if (easejs.Class.isA(DefaultHTMLForm, component)) {
        	this.uiComponent = component;
        }
	},
	
	'public static create' : function(c) {
		return new DefaultHTMLFormExecutor(c);
	 },
	
	'public virtual execute' : function() {
		if ( this.uiComponent != null ) {
			this.uiComponent.postConstruct();
			this.uiComponent.render();
			this.uiComponent.onCreationComplete();
			this.uiComponent.listeners();
			this.uiComponent.preDestroy();
		}
	}
});
