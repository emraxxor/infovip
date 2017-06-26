/**
 * @author attila
 */
var DefaultModalComponent = easejs.Class('DefaultModalComponent').extend(ModalComponent,{

	/**
	 * The value of the current element
	 */
	'private value' : null,

	/**
	 * Required property
	 * Input cannot be created without name
	 */
	'private inputName' : null,
	
	/**
	 * Optional property
	 * The component does not check if the ID exists on the page, it must be handle by the developer.
	 */
	'private inputID' : null,
	
    'override virtual __construct' : function () {
	     this.__super();
	},
	
	'public static create' : function() {
		return new DefaultModalComponent();
	},
	
	'public virtual setLabel' : function(text) {
		this.label = jQuery("<div></div>",{class : 'label-dialog-column-1', html: text})
		return this;
	},
	
	'public override virtual getLabel' : function() {
		return this.label;
	},
	
	'public override virtual getElement' : function() {
		return this.element;
	},

	'public virtual setInput' : function(_type,_name,_id,options) {
		if ( _id == undefined ) {
			_id = "";
		} else {
			this.inputID = _id;
		}
		
		if ( options == undefined )
			options = {};
		
		this.inputName = _name;
		
		this.element = jQuery("<div></div>",{class : 'label-dialog-column-2'})
		.append(
				jQuery("<input></input>", jQuery.extend({type:_type,name:_name,id:_id},options))
				.val(this.value)
		);
		return this;
	},

	'public virtual getValue' : function() {
		var ob  = this.element.find('input');
		
		if ( ob.length == 1 ) {
			return ob.val();
		}

		ob  = this.element.find('textarea');
		
		if ( ob.length == 1 ) {
			return ob.val();
		}

		return null;
	},
	
	'public virtual getInputName' : function() {
		return this.inputName;
	},
	
	'public virtual getInputID' : function() {
		return this.inputID;
	},
	
	'public virtual setValue' : function(val) {
		this.element.find('input').val(val);
		this.element.find('textarea').val(val);
		return this;
	},
	
	'public virtual setTextarea' : function(_name,_id,options) {
		if ( _id == undefined ) {
			_id = "";
		} else {
			this.inputID = _id;
		}
		
		if ( options == undefined )
			options = {};
		
		this.inputName = _name;
		
		this.element = jQuery("<div></div>",{class : 'label-dialog-column-2'})
		.append(
				jQuery("<textarea></textarea>", jQuery.extend({name:_name,id:_id},options))
				.val(this.value)
		);
		return this;
	},
	
});

//# sourceURL=/resources/js/core/modal/DefaultModalComponent.js

