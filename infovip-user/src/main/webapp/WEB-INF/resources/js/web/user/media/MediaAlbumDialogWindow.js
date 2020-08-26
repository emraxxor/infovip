/**
 * 
 * @author Attila Barna
 */
var MediaAlbumDialogWindow = easejs.Class('MediaAlbumDialogWindow').extend(BaseModalDialogWindow,{

	'public static DIALOG_WINDOW' : ApplicationScope.config.RESOURCES_PATH + '/mst/user/MediaAlbumModalWindow.mst',

	'private btnNewAlbum' : null,
	
	'private controller' : null,
	
    'override virtual __construct' : function(o) {
    	this.__super( MediaAlbumDialogWindow.$('DIALOG_WINDOW') );
    	this.controller = o;
    	this.setTitle('Create new album');
    	this.setWidth(80);
    	this.setHeight(60);
     },
     
 	'public override display' : function () {
 		
	 },


	'public override events' : function() {
		this.getBody().querySelector('.btn-upload-picture').addEventListener('click', x => this.onClickUploadButton(x) );
		this.getBody().querySelector('.btn-submit').addEventListener('click', x => this.onSubmit(x) );

	 },
	 
	'public onSubmit' : function(e) {
		 const that = this;
		 const name = this.getBody().querySelector("input[name=albumName]").value;
		 const photos = this.getBody().querySelectorAll(".photo");
		 const upload = (e) => that.async('/user/media/upload', e , (res,o) => {}, that ) ;

		 if ( photos.length == 0 ) {
    		 DefaultAlertDialog().display("You haven't uploaded any photos yet!", (ops, win) => {} , this );
			 return;
		 } 
		 
		 const w = DefaultInformationDialog().display(__tr('msg.loading'));
		 
		 this.async('/user/media/create', { name : name == "" ? 'Untitled' : name } , (res,o) => {
			 if ( res.documentId != undefined ) {
				 const ps = Array.from(photos).map( e => { return {
					    "id" : res.documentId,
					    "src" : Base64.encode( e.querySelector('img').src ), 
					    "name" : e.querySelector('input').value
					  }
				 } );
				 
				 ps.forEach( e => upload(e) );
				 
				 setTimeout( () =>  o.controller.getWall().reloadWaterfall() , 1500);
				 w.hide();
				 o.hide();
			 }
		 } , this);
		 
		 
		 
	 },
	 

	'public onClickUploadButton' : function(e) {
	    const inputElement = document.createElement("input");
	    inputElement.type = "file";
	    inputElement.accept = e;
	    inputElement.addEventListener("change", x => this.onHandleFileUpload(x) );
	    inputElement.dispatchEvent(new MouseEvent("click")); 
	 },
	 
	 'public addImage' : function(src) {
		 const images = this.getBody().querySelector('.pictures-list'); 
		 
		 const removeBtn = document.createElement('input');
		 removeBtn.type = "button";
		 removeBtn.value = "X";
		 removeBtn.classList.add('btn');
		 removeBtn.setAttribute('style','position: absolute;top:0;left:16px;');
		 removeBtn.addEventListener('click', x => x.target.parentElement.remove() );
		 
		 const imageDiv = document.createElement("div");
		 imageDiv.classList.add('col-md-3');
		 imageDiv.classList.add('mt-3');
		 imageDiv.classList.add('photo');
		 
		 const imageInput = document.createElement("input");
		 imageInput.type = "text";
		 imageInput.classList.add('form-control');
		 imageInput.placeholder = "Image name";
		 
		 
		 const image = document.createElement("img");
		 image.classList.add('img-thumbnail');
		 image.src = src;
		 
		 imageDiv.appendChild(image);
		 imageDiv.appendChild(imageInput);
		 imageDiv.appendChild(removeBtn);
		 
		 images.appendChild(imageDiv);
		 
	 },
	 
	 'public onHandleFileUpload' : function(x) {
		const fr = new FileReader();
		fr.addEventListener("load", e => this.addImage(e.target.result) );	 
		fr.readAsDataURL( x.target.files[0] );
	 }
	 	 

});
