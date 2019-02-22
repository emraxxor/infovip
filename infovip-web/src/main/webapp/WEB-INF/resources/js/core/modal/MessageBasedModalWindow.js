/**
 * @author Attila Barna
 * @type {MessageModalWindow}
 */
var MessageBasedModalWindow = easejs.Class('MessageBasedModalWindow').implement(IDialogWindow).extend(Controller,{

	/**
	 * Stores the message ("media") model
	 */
	'private panel' : null,
	
	/**
	 * Stores the media dialog
	 */
	'private modalDialog' : null,
	
	/**
	 * The current row in the dialog
	 */
	'private modalRow' : null,
	
	
	/**
	 * Content column
	 */
	'private contentColumn' : null,
	
	
	/**
	 * Messages column
	 */
	'private messageColumn' : null,
	
	
    /**
     * Stores the listeners
     */
    'protected listeners' : [],

    /**
     * Custom listeners
     */
    'protected componentListeners' : {},
    
    
    /**
     * Components
     */
    'protected components' : {},
    
    
    /**
     * The listeners that belong to the given component
     */
	'protected wListeners' : {},

	
	'override virtual __construct' : function() {
		this.listeners = [];
		this.componentListeners = {};
		this.wListeners = {};
		this.panel = jQuery("<div></div>", { "class" : "media--modal type--img modal fade in" });
		this.modalDialog = jQuery("<div></div>", { "class" : "modal-dialog" } );
		this.modalRow = jQuery("<div></div>", { "class" : "row gutter--0" } );
		this.contentColumn = jQuery("<div></div>", {  "class" : "col-md-8" } );
		this.messageColumn = jQuery("<div></div>", { "class" : "col-md-4" } );
		
  	 },

	 /**
	  * Hides the current window
	  **/
	 'public virtual hide' : function() {
        this.panel.modal("hide");

	  },

	/**
	 * Disposes the current window
	 **/
	 'public virtual dispose' : function() {
		this.panel.remove();
	  },
	
	
     'public virtual onReady' : function(callback,userData) {
    	 this.componentListeners['onReady'] = {callback:callback,userData:userData};
      },
      
      
      
     /**
      * Appends a new image to the modal dialog
      */
     'public virtual setImage' : function(img,text, alt) {
    	 
    	 if ( alt == undefined ) 
    		 alt = "";
    	 
    	 this.contentColumn.html('');
    	 
    	 this.contentColumn.append( 
    			 jQuery("<figure></figure>", { "class" : "media--img" } )
    			 .append(
    					 jQuery("<img></img>", { src : img , alt : alt } )
    					 .append(
    							 jQuery( "<p></p>", { "class" : "figcaption fs--14 fw--700 text-white", html : text } ) 
    					) 
    			  )
    	 );
      },
    	  
    	 
	 /**
	  * Displays the current dialog
	  * @virtual
	  */
     'public virtual display' : function (options) {
    	 var that = this;
    	 
    	 for(var k in this.components ) 
    		 this.modalDialog.append(this.components[k]);
    	 
    	 
    	 this.messageColumn.append(
    			 jQuery("<div></div>", { "class": "media--details" } )
    			 .append(
    					jQuery("<button></button>", {  "type" : "button", "class" : "close btn-link", "html" : "X" } )
    			 )
    			 .append(
    					jQuery("<div></div>", { "class" : "media--author clearfix" } )
    			 )
    	 );
    	 
    	 this.modalRow.append(this.contentColumn);
    	 this.modalRow.append(this.messageColumn);
    	 this.modalDialog.append(this.modalRow);
    	 
    	 this.panel.append(this.modalDialog);
    	 
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

         jQuery(document.body).append(this.panel);

         for(var i=0; i<this.listeners.length; i++) {
             var event = this.listeners[i];
             if ( event.element == undefined )
                 jQuery(this.currentDiv).on(event.type,event.params,event.callback);
             else if ( event.params != undefined )
                 jQuery(event['element']).on(event.type,event.params,event.callback);
         }

         jQuery(this.panel).on('hidden.bs.modal',function(){ that.dispose();});
         jQuery(this.panel).modal("show");

         
         if ( this.componentListeners.onReady != undefined ) 
         	this.componentListeners.onReady.callback(this,{ data: this.componentListeners.onReady.userData});
                 
         
         jQuery(document).off('keyup',this.keyListener).on('keyup',{caller:this},this.keyListener);
         
         this.onComponentCreationComplete();
      },
      
      
      /**
       * Adds a new component to the dialog
       * @param {component} JQuery object
       */
      'public addComponent' : function(name, component) {
    	 this.components[name] = component;  
      },

      /**
       * @todo
       */
 	 'public virtual keyListener' : function(event) {},
      
      /**
       * Removes the given component from the dialog
       */
     'public removeComponent' : function(name) {
    	  this.components[name].remove();
    	  this.components[name] = null;
      },
      
      'public addComponentListener' : function(type,func,args) {
      	  this.wListeners[type] =   { f : func , a : args };
      },
      
      'public addCreationCompleteEvent' : function(func) {
      	  this.wListeners['creationComplete'] = func;
      },

  	  'public virtual onComponentCreationComplete' : function() {
  		var that = this;
  		  
  		if ( this.wListeners['creationComplete'] != undefined ) 
  			this.wListeners['creationComplete'](this);
  		
  		
  		for( var k in this.wListeners ) {
  			if ( k == 'creationComplete' )
  				continue;
  			
  			this.wListeners[k].f( this ,  this.wListeners[k].a  ); 
  		}
  		
  		
  		this.messageColumn.find('button.close').on('click', function(e){ that.hide(); } );
  		
  		
      },

});