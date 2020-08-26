/**
 * 
 * @author Attila Barna
 */
var PhotoViewer = easejs.Class('PhotoViewer').extend(UIController,{

	'private controller' : null,
	
	'private container' : null,
	
    'override virtual __construct' : function(controller) {
    	this.controller = controller;
    	this.container = document.querySelector("#mediawaterfall");
     },

    'public override virtual render' : function() {
    	this.__super();
     },
    
    'private updateItem' : function(item) {
    	const img = item.querySelector('.img-thumbnail');
    	const data = JSON.parse( Base64.decode(item.dataset.encoded) );
    	item.attributes.emarker = true;
    	item.addEventListener('click' , e =>  UIControllerExecutor(  PhotoDialogWindow(this.controller,data, e) ).execute() );
    },
     
    'public virtual update' : function() {
    	Array.from(this.container.querySelectorAll("div.item")).filter(e => e.attributes.emarker === undefined )
    		.forEach( e => this.updateItem(e) );
     },
     
	'public override virtual events' : function() {
		
	 },
	 
	'public override virtual onCreationComplete' : function() {
		this.__super();
	 }

});