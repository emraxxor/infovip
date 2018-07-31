/**
 * Created by attila on 1/3/17.
 */

/**
 * Creates a dialog window using the bootstrap library
 *
 */
var DefaultDialogWindow = easejs.Class('DefaultDialogWindow').extend(DialogWindow,{

    /**
     * The name of the current window
     */
    'protected windowName' : '',


    /**
     * The footer for the current window
     */
    'protected footer' : '',

    /**
     * Current div
     */
    'protected currentDiv' : null,

    /**
     * Stores the listeners
     */
    'protected listeners' : [],

    /**
     * Custom listeners
     */
    'protected componentListeners' : {},
   
    /**
     * ID of the WARNING dialog
     */
    'public static WARNING_MESSAGE' : 0x01,
    
    /**
     * ID of the CONFIRM dialog
     */
    'public static CONFIRM_MESSAGE' : 0x02,
    
    /**
     * Id of the INFORMATION dialog (it can't be closed by the user) 
     * If it is specified for the dialog then the dialog should be closed by the hide method
     */
    'public static INFORMATION_MESSAGE' : 0x03,

    'override virtual __construct' : function (id) {
        this.__super(id);
        this.listeners = [];
        this.componentListeners = {};
    },

    'public static showMessageDialog' : function(title,message,type,fcallback,params) {
    	var dWindow = new DefaultDialogWindow("GeneralMessageWindowDialog" + jQuery("div[id*='GeneralMessageWindowDialog']").length + 1 );
    	dWindow.setTitle(title);
    	dWindow.setText(jQuery("<div></div>",{class:'global-warning-message-body'}).html(message));
    	
    	if ( type == DefaultDialogWindow.$('WARNING_MESSAGE') ) {
    		dWindow.setFooter(
    				jQuery("<div></div>",{class : 'global-warning-message-footer'})
            		.append(jQuery("<input></input>",{type:'button',value:'OK','btn-model':'OK', class:'btn btn-success'}))
            		.append(jQuery("<input></input>",{type:'button',value:'Cancel','btn-model':'CANCEL',class:'btn btn-primary'}))
            );
    	}
    	
    	if ( type == DefaultDialogWindow.$('CONFIRM_MESSAGE') ) {
    		dWindow.setFooter(
    				jQuery("<div></div>",{class : 'global-confirm-message-footer'})
            		.append(jQuery("<input></input>",{type:'button',value:'OK','btn-model':'CONFIRM', class:'btn btn-success'}))
            		.append(jQuery("<input></input>",{type:'button',value:'Cancel','btn-model':'CANCEL',class:'btn btn-primary'}))
            );
    	}
    	
    	
    	if ( type == DefaultDialogWindow.$('INFORMATION_MESSAGE') ) {
    		dWindow.setFooter(
    				jQuery("<div></div>",{class : 'global-confirm-message-footer'})
            		.append(jQuery("<input></input>",{type:'button',value:'OK','btn-model':'CONFIRM', class:'btn btn-success'}))
            );
    	}

    	
    	dWindow.display();
    	dWindow.getWindow().css('top','30%').css('left','25%').css('width','50%');
    	
    	var inputs = dWindow.getInputsAsArray();
    	for(var i = 0; i<inputs.length;i++) {
    		var input = jQuery(inputs[i]);
    		if ( input.attr('btn-model') != undefined && (input.attr('btn-model') == 'OK' || input.attr('btn-model') == 'CANCEL' ) ) {
    			input.click(function(){dWindow.hide()});
    		} else if ( input.attr('btn-model') != undefined && (input.attr('btn-model') == 'CONFIRM'  ) )  {
    			input.off('click').on('click',function(e){ fcallback(params,dWindow);});
    		}	
    	}
    	
    	return dWindow;
    },
    
    /**
     * Displays the current dialog
     * @virtual
     */
    'public override virtual display' : function (options) {
        var that = this;

        if ( jQuery("#" + this.dialogID).length > 0  ) {
            throw new Error("You cannot use two id at the same time on the same page!!!");
            return;
        }

        this.currentDiv = jQuery("<div></div>",{id : this.dialogID, 'class': 'modal fade', role: 'dialog'})
        	.append( 
        		jQuery("<div></div>",{"class":"modal-dialog"})		
        		.append(
        				
        				jQuery("<div></div>",{"class":"modal-content","style":"width:100%"})
        				.append(
        						jQuery("<div></div>",{"class": 'modal-header'})
        						.append(
        								jQuery("<h4></h4>",{html: this.windowName})
        						)
        				)
        				.append(
        						jQuery("<div></div>",{"class": 'modal-body', html: this.text})
        				)
        				.append(
        						jQuery("<div></div>",{"class": 'model-footer', html: this.footer})
        				)
        		)
        	);

        
        if ( options != undefined ) {
        	if ( options.minWidth != null ) {
        		this.currentDiv.find(".modal-dialog").css("minWidth",options.minWidth);
        	}
        	
        	if ( options.width != null ) {
        		this.currentDiv.find(".modal-dialog").css("width",options.width);
        	}
        	
        	if ( options.height != null ) {
        		this.currentDiv.find(".modal-dialog").css("height",options.height);
        	}
        }
        
        jQuery(document.body).append(this.currentDiv);

        for(var i=0; i<this.listeners.length; i++) {
            var event = this.listeners[i];
            if ( event.element == undefined )
                jQuery(this.currentDiv).on(event.type,event.params,event.callback);
            else if ( event.params != undefined )
                jQuery(event['element']).on(event.type,event.params,event.callback);
        }

        jQuery(this.currentDiv).on('hidden.bs.modal',function(){ that.dispose();});
        jQuery(this.currentDiv).modal("show");

        
        if ( this.componentListeners.onReady != undefined ) 
        	this.componentListeners.onReady.callback(this,{ data: this.componentListeners.onReady.userData});
                
        
        jQuery(document).off('keyup',this.keyListener).on('keyup',{caller:this},this.keyListener);
        
        this.onComponentCreationComplete();
        
    },
    
    'public virtual getBody' : function() {
    	return jQuery(this.currentDiv.find('div[class=modal-body]')[0]);
    },
    
    /**
     * Key listener for the current dialog window
     */
    'public virtual override keyListener' : function(e) {
    	if ( e.keyCode == 27 ) {
    		e.data.caller.hide();
    	}
    },
    
    
    /**
     * Gets the modal dialog
     */
    'public getModal' : function() {
    	return jQuery(this.currentDiv);
    },

    /**
     * Appends an event listener
     */
    'public addEventListener' : function(type,callback,params,xpath) {
        if ( xpath == undefined) {
            this.listeners.push({type: type, callback: callback, params:params});
        } else {
            this.listeners.push({element:xpath,type:type,callback:callback,params:params});
        }
    },

    /**
     * Disposes the current window
     */
    'public virtual override dispose' : function() {
        this.currentDiv.remove();
    },

    'public virtual override hide' : function() {
        jQuery(this.currentDiv).modal("hide");
    },


    'public virtual override onReady' : function(callback,userData) {
    	 this.componentListeners['onReady'] = {callback:callback,userData:userData};
     },
     
     /**
      * Gets the specified element of the window
      */
    'public getElement' : function(element) {
    	this.currentDiv.find(element);
    },
    
    /**
     * Gets the input by the given name
     */
    'public getInput' : function(inputName) {
    	return this.currentDiv.find("input[name='"+inputName+"']");
    },
    
    /**
     * Gets the input list as an array list
     */
    'public getInputsAsArray' : function() {
    	return jQuery.map( this.currentDiv.find("input") , function(ob,id) {  
    		if ( jQuery(ob).attr('type') == 'checkbox'  ) {
    			if ( jQuery(ob).is(':checked')  ) {
    				jQuery(ob).val(true);
    			} else {
    				jQuery(ob).val(false);
    			}
    		} 
    		return ob; 
    		
    	});
    },
    
    
    /**
     * Gets the current window
     */
    'public getWindow' : function() {
    	return this.currentDiv;
    },
    
    'public setWindow' : function(val) {
    	this.currentDiv = val;
    },
    
    /**
     * Sets the name of the window
     * @param windowName
     * @final
     */
    'public setTitle' : function (title) {
        this.windowName = title;
    },

    /**
     * Sets the footer of the current dialog
     * @param footer
     */
    'public setFooter' : function(footer) {
        this.footer = footer;
    }
    
    
});


//# sourceURL=/resources/js/core/dialog/DefaultDialogWindow.js
