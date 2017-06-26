/**
 * @author attila
 * @type {ModalDialogWindow}
 */
var ModalDialogWindow = easejs.Class('ModalDialogWindow').implement(IDialogWindow).extend(Controller,{
	
	/**
	 *  The current modal dialog window
	 */
	 'protected modalDialog' : null,

	 /**
	  * Should be the parent object
	  */
	 'protected parent' : null,

	 /**
	  * Should be the data that is used within the current component
	  */
	 'protected data' : null,
	 
	 /**
	  * The unique identifier of the dialog
	  */
	 'protected modalDialogID' : null,
	
	 /**
	  * Title of the dialog
	  */
	 'protected title' : null,
	 
	 /**
	  * Sub components, should be an instance of ModalComponent
	  */
	 'private components' : [],
	
	 /**
	  * Unique data 
	  */
	 'private isOverflow' : null,
	 
	 /**
	  * Contains the css data for the outer window
	  */
	 'private windowData' : {}, 
	 
	 /**
	  * Contains the css data for the current window
	  */
	 'private cssData' : {},
	 
	 'override virtual __construct' : function (parent,id) {
	        this.__super();
	        this.parent = parent;
	        this.modalDialogID = id;
	        this.isOverFlow = true;
	        this.windowData = { "minWidth" : "96%", "width" : "96%", "left":"396px", "maxHeight" : "600px"};
	        this.cssData = {};
	  },
	 
	 'public getID' : function() {
		 return this.modalDialogID;
	  },
	
	 'public virtual beforeDestroy' : function() {},
	  
	 'public virtual beforeCreationComplete' : function() {},
	
	 'public virtual initComponents' : function() {},
	 
	 'public setOverFlow' : function(boolval) {
		 this.isOverflow = boolval;
		 return this;
	 },
	 
	 'public setWindowData' : function(key,val) {
		 this.windowData[key] = val;
		 return this;
	 },
	 
	 'public style' : function(key,val) {
		 this.cssData[key] = val;
		 return this;
	 },
	 
	 'public setTitle' : function(title) {
		 this.title = title;
		 return this;
	 },
	 
	 'public getTitle' : function() {
		 return this.title;
	 },
	 
	 'public addComponent' : function(component) {
		 this.components.push(component); 
		 return this;
	  },
	  
	 'public getComponents' : function() {
		  return this.components;
	  },
	  
	 'public getInputByName' : function(inputName) {
		 for( i in this.components) {
			 var c = this.components[i];
			 if ( c.getInputName() == inputName ) {
				 return c;
			 }
		 }
		 return null;
	 },
	 
	 'public getInputByID' : function(inputID) {
		 for( i in this.components) {
			 var c = this.components[i];
			 if ( c.getInputID() != null && c.getInputID() == inputID ) {
				 return c;
			 }
		 }
		 return null;
	 },
	 
	 /**
	  * Size  of the components
	  */
	 'public size' : function() {
		 return this.components.length;
	 },
	  
	 'public getComponentAt' : function(index) {
		 return this.components[index];
	  },
	 
	 'public indexOfComponent' : function(component) {
		 return this.components.indexOf(component);
	 },
	 
	 'public removeComponentAt' : function(index) {
		 this.components.splice(index,1);
		 return this;
	 },
	 
	 'public deleteComponentAt' : function(index) {
		delete this.components[index];
		return this;
	 },
	  
	 'public create' : function() {
	    	this.modalDialog =  new DefaultDialogWindow(this.modalDialogID);
	        this.modalDialog.setTitle(this.title);
	        
	        var body = jQuery("<div></div>",{class:'modal-window-container'});
	        
	        for ( var i=0; i<=this.components.length;i++) {
	        	var comp = this.components[i];
	        	if ( easejs.Class.isA( ModalComponent , comp ) ) {
	        		if ( comp.getLabel() != null ) {
	        			body.append( comp.getLabel() );
	        		}
	        		
	        		if ( comp.getElement() != null ) {
	        			body.append(comp.getElement());
	        		}
	        	}
	        }
	        
	        this.modalDialog.setText(body);
	       
	        this.footer();
	        
	        this.modalDialog.onReady(function(_window,e){
	        	e.data.caller.onCreationComplete(_window)
	        },{caller:this});
	        
	        return this;
	 },
	 
	 'public virtual footer' : function() {
	        this.modalDialog.setFooter(
	        		jQuery("<div></div>",{class : 'modal-window-footer'})
	        		.append(jQuery("<input></input>",{type:'button',value:'Mentés',name: 'buttonSubmit','class':'btn btn-success'}))
	        		.append(jQuery("<input></input>",{type:'button',value:'Bezár',name: 'buttonCancel', 'class':'btn btn-primary'}))
	        );
	        
	        this.modalDialog.addEventListener('click',this.clickOnSubmit, { caller : this}, DialogWindow.inputName('buttonSubmit') );
	        this.modalDialog.addEventListener('click',this.clickOnCancel, { caller : this}, DialogWindow.inputName('buttonCancel') );
	 },
	
	 'public virtual hide' : function() {
		 this.modalDialog.hide();
	 },
	 
	 'public virtual onReady' : function(callbackMethod,data) {
    	 throw new Error("Not implemented by default!");
	  },
	  
	 'public virtual dispose' : function() {
	        this.modalDialog.dispose();
	  },
	 
	 /**
	  * Gets the value of the specified input
	  */
	 'public getValue' : function(name) {
		 var ob = this.modalDialog.find('input[name='+name+']'); 
		 if ( ob.length == 1 ) {
			 return jQuery(this.modalDialog.find('input[name='+name+']')[0]);
		 }
		 
		 ob = this.modalDialog.find('textarea[name='+name+']'); 
		 if ( ob.length == 1 ) {
			 return jQuery(this.modalDialog.find('textarea[name='+name+']')[0]);
		 }
		 
		 return null;
		 
	 },
	 
	 'public setData' : function(val) {
		 this.data = val;
		 return this;
	 },
	 
	 'public getData' : function() {
		return this.data; 
	 },
	 
	 'public virtual close' : function() {
	     this.beforeDestroy();   
		 this.modalDialog.hide();
	  },

	 'public getParent' : function() {
		  return this.parent;
	  },
	    
	 'public virtual clickOnCancel' : function (e) {
	        e.data.caller.close();
	  },

	 'public virtual display' : function(options) {
			this.modalDialog.display({ "minWidth" : this.windowData['minWidth'], "width": this.windowData['width']});
			
			if ( this.isOverFlow ) {
				this.modalDialog.getBody().css("overflow","auto");
			}
			
			this.modalDialog.getBody().css("maxHeight",this.windowData['maxHeight']);
			this.modalDialog.getWindow().css("left", this.windowData['left']);
			
			for ( i in this.cssData ) {
				this.modalDialog.getBody().css(i,this.cssData[i]);
			}
			
	    	return this;
	  },

	 'public virtual clickOnSubmit' : function(evt) {
		 var that = evt.data.caller;
		 that.close();
	  },
	  
	 'public virtual addEventListener' : function(type,callback,caller,element) {
		this.modalDialog.addEventListner(type,callback,{'caller':caller},element); 
	 },
	 
	 'public virtual keyListener' : function(event) {},
	 
	 'public virtual focusOn' : function(modalWindow) {},
	 
	 'public virtual onCreationComplete' : function(_window) {
		 var inputs = _window.getInputsAsArray();
	     var that = this;
	     
     	 for(var i=0; i < inputs.length; i++) {
     		var input = jQuery(inputs[i]);
     		if ( input.attr('type') == 'text' || input.attr('type') == 'password' ) {
					input.on('keypress', { caller: that } , function(e){
						if ( e.which == 13 ) {
							that.clickOnSubmit(e);
						}
					} );
     		}
     	}
     	 
     	_window.getModal().on('shown.bs.modal', function(){
     		that.focusOn($(this));
         }); 
	  }

});

//# sourceURL=/resources/js/core/ModalDialogWindow.js

