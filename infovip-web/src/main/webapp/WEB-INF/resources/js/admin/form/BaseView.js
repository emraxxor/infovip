/**
 * @author attila
 */
var BaseView = easejs.AbstractClass('BaseView').extend(Controller,{

	'protected view' : null,
	
    'override virtual __construct': function () {
    	this.__super();
    },

    'public virtual postInitialization' : function() {
    	this.view = this.viewDefinition();
    },
    
    'public virtual onCreationComplete' : function(ui,baseform) {},
    
    /**
     * The implementation of the view, the implementation of the method must to return with a webix.ui object
     */
	'public abstract viewDefinition' : [],

	
	/**
	 * Check if an element exists in the data
	 */
	'public exists' : function(source,data,dataColumn,srcColumn) {
		for ( i in data ) {
			if ( data[i][dataColumn] == source[srcColumn] ) {
					return true;
			}
		}
		return false;
	},
	
	'public getObject' : function(list,col,value) {
		for ( i in list ) {
			if( list[i][col] == value ) {
				return list[i];
			}
		}
		return null;
	}, 
	
	'public virtual getView' : function() {
		return this.view;
	},
	
	'public virtual setView' : function(view) {
		this.view = view;
	}

});
