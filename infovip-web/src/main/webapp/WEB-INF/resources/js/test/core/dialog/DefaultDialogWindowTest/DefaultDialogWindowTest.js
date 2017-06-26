var DefaultDialogWindowTest = easejs.Class('DefaultDialogWindowTest').extend(Controller,{
	
	'private dialogWindow' : null,
	
	'override __construct' : function () {
        this.__super();
    },
    
    'public create' : function(redirectURL) {
    	this.dialogWindow =  new DefaultDialogWindow('dialogWindow');
        this.dialogWindow.setTitle("DialogWindow Title");
        this.dialogWindow.setText(
        		jQuery("<div></div>",{class:'window-container'})
        		.append(
        				jQuery("<div></div>",{class : 'label', html: 'Label 1:'})
        				
        		)
        		.append(
        				jQuery("<div></div>",{class : 'panel-input'})
        				.append(
        						jQuery("<input></input>",{type:'text','name': 'anything'})
        				)
        		)
        		.append(
        				jQuery("<div></div>",{class : 'label', html: 'Label 2:'})
        				
        		)
        		.append(
        				jQuery("<div></div>",{class : 'panel-input'})
        				.append(
        						jQuery("<input></input>",{type:'password','name': 'password', style:'max-width:90%;'})
        				)
        				
        		)
        );
        
        this.dialogWindow.setFooter(
        		jQuery("<div></div>",{class : 'login-window-footer'})
        		.append(jQuery("<input></input>",{type:'button',value:'BTN 1',name: 'btn1',class:'btn btn-success'}))
        		.append(jQuery("<input></input>",{type:'button',value:'BTN 1',name: 'btn2',class:'btn btn-success'}))
        		.append(jQuery("<input></input>",{type:'button',value:'BTN CLOSE',name: "btn3",class:'btn btn-primary'}))
        );
        
        this.dialogWindow.addEventListener('click', this.onCancel, {caller : this}, DialogWindow.inputName('btn3') );
        
        this.dialogWindow.onReady(function(_window,e){
        	var inputs = _window.getInputsAsArray();
        	for(var i=0; i < inputs.length; i++) {
        		var input = jQuery(inputs[i]);
        		if ( input.attr('type') == 'text' || input.attr('type') == 'password' ) {
					input.on('keypress', { caller: e.data.caller } , function(e){
						if ( e.which == 13 ) {
							e.data.caller.onSubmit(e);
						}
					} );
        		}
        	}
        	
        	_window.getModal().on('shown.bs.modal', function(){
                jQuery(this).find('input[name="btn1"]').focus();
            });

        },{caller:this});
        
        return this;
    },
    
    'public close' : function() {
        this.dialogWindow.hide();
    },

    'public onCancel' : function (e) {
        e.data.caller.close();
    },
    
    'public onSubmit' : function(e) {
    	var caller = e.data.caller;
    	var window = caller.loginWindow;
    	alert('submit');
    },

    'public display' : function(options) {
    	this.dialogWindow.display(options);
    }

});

