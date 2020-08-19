/** 
 * @author Attila Barna
 */
var BaseModalDialogWindow = easejs.Class('BaseModalDialogWindow').extend(UIController,{
	
	'private closeCallback' : null,

	'private panel' : null,

	'private window' : null,

	'private body' : null,
	
	'private template' : null,
	
	'private dialog' : null,
	
	'private header' : null,
	
    'virtual override __construct' : function(template) {
    	const base = this.load( ApplicationScope.config.RESOURCES_PATH  + '/mst/base/BaseDialogWindow.mst' ,  {}, 'GET'  );
    	this.template = this.load( template , {}, 'GET'  );
        this.panel = jQuery("<div></div>");
        this.window = jQuery(Mustache.render( base  , { }));
        this.header = this.window[0].querySelector('.modal-header');
        this.dialog = this.window[0].querySelector('.modal-dialog');
        this.body = this.window[0].querySelector('.modal-body');
        this.panel.append( jQuery( Mustache.render( this.template, {} ) ) ); 
		this.window.find('.modal-body').append(this.panel);

     },

     'public setCloseOperation' : function(fn) {
     	this.closeCallback = fn;
     	return this;
     },
 
     'public onClose' : function() {
    	 if ( this.closeCallback !== null )
    		 this.closeCallback(this);
     },
     
     'public getHeader' : function() {
    	 return this.header;
     },
     
     'public getDialog' : function() {
    	 return this.dialog;
     },
     
     'public setTitle' : function(title) {
    	 this.header.querySelector('.modal-title').innerHTML = title;
     },
     
    'public getBody' : function() {
    	 return this.body;
     },
     
     
     /**
      * Sets the minimal width of the dialog
      */
    'public setWidth' : function(percent) {
    	this.dialog.style.minWidth = Math.floor(window.screen.width * (percent/100)) + 'px' ;
     },
     
     /**
      * Sets the minimal height of the dialog
      */
    'public setHeight' : function(percent) {
    	this.body.style.minHeight = Math.floor(window.screen.height * (percent/100)) + 'px' ;
     },
     
     /**
      * Supported bootstrap dialog classes 
      *  - modal-xl and so on..
      */
    'public setSizeClass' : function(sizeclass) {
    	this.dialog.classList.add(sizeclass);
     },
     
  	'public virtual display' : function () { },
	
    'public override virtual events' : function() { },
	 	 
	'public override virtual onCreationComplete' : function() {
		const that = this;
		
		this.display();

		this.window.on('hidden.bs.modal',function(){
			that.close();
			that.window.remove();
		});
		
		this.window.find('.btn-dialog-close').on('click', x => this.window.modal('hide'));
		this.window.modal('show');
		this.window.on('shown.bs.modal', e => this.onComponentCreationComplete() );
	 },
	 
	'public onComponentCreationComplete' : function() {		
	 },
	
	 
	'public close' : function() {
			this.onClose();
	 }


});
