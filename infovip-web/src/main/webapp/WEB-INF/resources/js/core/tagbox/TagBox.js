/**
 * @author Attila Barna
 *
 * A Simple tagbox component allows you to create a ui interface that is suitable for appearing selectable elements.
 * Tagbox element can be any object, the point is that the object includes a value "field" that defines the value to be displayed.
 * 
 * For instance:
 * tagbox.add( { anyattribute : anyvalue , value : "display" } );
 *
 * You can change the value of the field to be displayed to anything else at any time.
 * tagbox.changeValueField('newfield');
 *
 * Remaining objects can be queried at any time using the getValues method.
 * tagbox.getValues();
 *
 * It is necessary to emphasize the elements that have been already added to the list become removable. 
 * This process can be handled by various triggers. 
 * 
 */

var TagBox = easejs.Class('TagBox').extend(Controller,{
	
	'protected data' : null,
	
	'protected valueField' : 'value',
	
	'private width' : '90%',
	
	'private height' : '90%',
	
	'private panel' : null,
	
    'override virtual __construct': function () {
    	this.__super();
    	this.data = new Array();
    	this.panel = jQuery("<div></div>",{class:"chzn-container chzn-container-multi chzn-container-active"})
    				 .css({"width":this.width,"height":this.height});
     },
    
    'public virtual add' : function(o) {
    	this.data.push(o);
    },
    
    'public virtual changeValueField' : function(fieldName) {
    	this.valueField = fieldName;
    },
    
    'public getView' : function() {
    	return this.panel;
    },
    
    'public virtual redraw' : function() {
    	var that = this;
    	var ul = jQuery("<ul></ul>", {class:"chzn-choices"});
    	for(var i in this.data) {
    		var o = this.data[i];
    		ul.append( 	
    				jQuery("<li></li>",{class:"search-choice", "x-data" : Base64.encode(JSON.stringify(o)) })
    				.append(jQuery("<span></span>",{html:o[this.valueField]}))
    				.append(jQuery("<a></a>",{href: "javascript:void(0);", class: "search-choice-close" }))
    		);
    	}
    	this.panel.html(ul);
    	
    	this.panel.find('li').each(function(){
    		jQuery(this).find('a[class=search-choice-close]').each(function(){
    			jQuery(this).on('click',function(){
    				var b = jQuery(this);
    				var k = jQuery.parseJSON( Base64.decode( b.parent('li').attr('x-data') ) );
    				that.remove( k[that.getValueField()], that.getValueField() );
    				b.parent('li').remove();
    			});
    		});
    	});
    	
    },
    
    'public virtual getValueField' : function() {
    	return this.valueField;
    },
    
    'public virtual setVisible' : function(v) {
    	if ( v == true ) {
    		this.redraw();
    	} else {
    		this.panel.html("");
    	}
    },
    
    'public sort' : function(f) {
    	this.data.sort(f);
    },
    
    'public virtual removeView' : function() {
    	this.panel.remove();
    },
    
    'public virtual clearAll' : function() {
    	this.data = new Array();
    	this.panel.html("");
    },
    
    'public virtual remove' : function(value, field) {
    	if ( field != undefined ) {
    		field = this.valueField;
    	}
    	
    	 var i = this.indexOf(value,field);
    	 if ( i > -1 ) {
    		 this.data.splice(i,1);
    	 }
    },

    'public virtual indexOf' : function(value, field) {
    	var i = 0;
    	
    	if ( field == undefined ) {
    		field = this.valueField;
    	}
    	
    	for(i=0; i < this.data.length; i++ ) {
    		if ( this.data[i][field] == value  ) {
    			return i;
    		}
    	}
    	
    	return -1;
    },
    
    'public virtual getData' : function() {
    	return this.data;
    },
    
    'public virtual getValues' : function() {
    	return this.getData();
    },
    
    'public virtual setValues' : function(data) {
    	this.data = data;
    },
    
    'public virtual exists' : function( ob , markerField ) {
    	if ( markerField == undefined ) {
    		markerField = this.valueField;
    	}
    	
    	for(var i in this.data) {
    		var e = this.data[i];
    		if ( ob[markerField] == e[markerField] ) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    	
});
