/**
 * @author Attila Barna
 */
var ModalComponent = easejs.Class('ModalComponent').extend(Controller,{
	
	'protected element' : null,
	
	'protected label' : null,
	
    'override virtual __construct' : function () {
	     this.__super();
	},

	'public virtual getLabel' : function() {
		return this.label;
	},
	
	'public virtual getElement' : function() {
		return this.element;
	}
	
});

//# sourceURL=/resources/js/core/modal/ModalComponent.js
