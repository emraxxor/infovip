/**
 * @author Attila Barna
 */
var UserProfileAvatarEditorWindow = easejs.Class('UserProfileAvatarEditorWindow').extend(Controller,{

	'public static DIALOG_WINDOW' : ApplicationScope.config.RESOURCES_PATH + '/mst/user/UserProfileAvatarModalWindow.mst',
	
	'private dialogTemplate' : null,
	
	'private panel' : null,

	'private window' : null,
	
	'private domElement' : null,
	
	'private closeCallback' : null,
	
	'private dataElement' : null,
	
	'private data' : null,
	
	'private user' : null,
	
	'private imageData' : null,
	
    'virtual override __construct' : function (de,user) {
    	const template = this.load( UserProfileAvatarEditorWindow.$('DIALOG_WINDOW') , {}, 'GET'  );
    	this.domElement = de;
    	this.user = user;
        this.panel = jQuery("<div></div>");
        this.window = jQuery(Mustache.render( template  , { }));
    },
    
    'public virtual getPanel' : function() {
    	return this.panel;
    },
    
    'public setCloseOperation' : function(fn) {
    	this.closeCallback = fn;
    	return this;
    },
    
    'public onClose' : function() {
    	if ( this.closeCallback !== null )
    		this.closeCallback(this);
     },
     
    'public getList' : function() {
    	 return this.list;
     },
    
     
     'public convertToBase64' : ( file , callback ) =>  {
    	 const reader = new FileReader();
    	 reader.readAsDataURL(file);
    	 reader.onload = function () {
    	     callback(reader);
    	 };
    	 
    	 reader.onerror = function (error) {
    	     console.log('Error: ', error);
    	 };
     },

    'public setImageData' : data => {
    	this.imageData = data;
     },
     
     'public getImageData' : () => {
    	 return this.imageData;
     },
     
	'public display' : function () {
		var that = this;
        
        this.window.on('hidden.bs.modal',function(){
        	that.close();
    		that.window.remove();
        });

        const body = this.window.find('.modal-body')[0];
        const content = this.window.find('.modal-content')[0];
        const avatar = this.window.find('.cover--avatar')[0];
        
        
        this.listeners(content);
        
        body.style.width = "80%";
        body.style.width = "80%";
        body.style.margin = "0 auto";

		if ( this.user.picture != null ) 
			this.window.find('.cover--avatar')[0].querySelector('img').setAttribute('src','/user/image?' + this.user.picture);

		
        this.window.find('.btn-dialog-close').on('click', x => this.window.modal('hide'));
        this.window.find('.modal-body').last().find('.modal-content').last().append(this.panel);
        this.window.modal('show');
        this.window.on('shown.bs.modal', e => this.onComponentCreationComplete() );
	},

	'private reload' : function(options) {
		const that = this;
	 },
	 

	
	'private listeners' : function(content) {
		const that = this;
		const file = content.querySelector('[type=file]');
		
		file.addEventListener('change', e => {
			this.convertToBase64(file.files[0], r => {
				const avatar = content.querySelector('.cover--avatar');
				avatar.querySelector('img').setAttribute('src',r.result);
				that.setImageData(r.result);
			} );			
		} );
		
		
		content.querySelector('.btn-success').addEventListener('click', e => {
			if ( that.getImageData() != null ) {
			     that.async('/user/profile/update', { picture : that.getImageData() }, (r,o) => {
			    	 if ( r.code != undefined && r.code == 1 ) {
			    		 DefaultAlertDialog().display(__tr('msg.upload.complete'), (ops, win) => {
			    			 that.hide();
			    		 } , o );
			    	 } else if ( r.code != undefined && r.message != undefined ) {
			    		 DefaultAlertDialog().display( r.message, (ops, win) => {
			    			 that.hide();
			    		 } , o );
			    	 } 
			    	 
			     }, that);	
			} else {
				that.hide();
			}
		});
	 },
	
	'public hide' : function() {
		 this.window.modal('hide');
	 },
	 
	'public onComponentCreationComplete' : function() {		
		this.reload();
	 },
	
	'public close' : function() {
		this.onClose();
	 }
});