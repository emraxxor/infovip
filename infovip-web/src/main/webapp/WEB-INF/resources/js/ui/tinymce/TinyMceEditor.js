/**
 *  Simple tinyMce wrapper
 *  @author Attila Barna
 *  
 */
var TinyMceEditor = easejs.Class('TinyMceEditor').extend(Controller,{
	
	'protected node' : null,
	
    'override virtual __construct' : function () {
    	this.__super();
    	this.node = jQuery('<textarea></textarea>');
     },
     
     'public getText' : function() {
     	return tinyMCE.get(this.node.attr('id')).getContent();
     },
     
 	'public clear' : function() {
 		tinyMCE.get(this.node.attr('id')).setContent('');
	 },

	 'public setText' : function(v) {
		 tinyMCE.get(this.node.attr('id')).setContent(v);
	 },

     'public getNode' : function() {
    	 return this.node;
      },
      
     'public full' : function(width,height) {
      	
    	if ( width == undefined )
      		width = '100%';
      	
      	if ( height == undefined ) 
      		height = '300';
      	
      	tinymce.init({
      				  target : this.node[0],
      				  plugins: [
  							"advlist autolink link image lists charmap print preview hr anchor pagebreak",
  							"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
  							"table contextmenu directionality emoticons template textcolor paste textcolor colorpicker textpattern"
  					  ],
  					  toolbar1: "newdocument | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
  					  toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | link unlink anchor image media code | insertdatetime preview | forecolor backcolor",
  					  toolbar3: "table | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | ltr rtl | visualchars visualblocks nonbreaking template pagebreak restoredraft",
  					  language: 'hu_HU',
  					  doctype: '',
  					  width: width,
  					  height: height,
  					  relative_urls : false,
  					  remove_script_host : false,
  					  convert_urls : true,
  					  images_upload_url: TinyMceManager.$('HANDLER').USER_IMAGE,
  					  images_upload_handler: function (blobInfo, success, failure) {
  						   var resp = that.load( TinyMceManager.$('HANDLER').USER_IMAGE  , {data : blobInfo.base64() } );
  						   if ( resp.name != null ) {
  							   setTimeout(function() {
  								  success(ApplicationScope.config.APPLICATION_URL + '/' + ApplicationScope.config.FILE_SERVLET+"?"+resp.name);
  							   }, 2000);
  						   }
  					  }
      		  });
      },

     
     'public basic' : function(width,height) {
    	 
     	if ( width == undefined )
     		width = '100%';
     	
     	if ( height == undefined ) 
     		height = '300';
     	
     	tinymce.init({
     				  target : this.node[0],
     				  menubar: false,
     				  plugins: [
     				    'advlist autolink lists link image charmap print preview anchor textcolor',
     				    'searchreplace visualblocks code fullscreen',
     				    'insertdatetime media table contextmenu paste code help wordcount'
     				  ],
     				  toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
 					  doctype: '',
 					  width: width,
 					  height: height,
 					  relative_urls : false,
 					  remove_script_host : false,
 					  convert_urls : true,
 					  images_upload_url: TinyMceManager.$('HANDLER').USER_IMAGE,
 					  images_upload_handler: function (blobInfo, success, failure) {
 						   var resp = that.load( TinyMceManager.$('HANDLER').USER_IMAGE  , {data : blobInfo.base64() } );
 						   if ( resp.name != null ) {
 							   setTimeout(function() {
 								  success(ApplicationScope.config.APPLICATION_URL + '/' + ApplicationScope.config.FILE_SERVLET+"?"+resp.name);
 							   }, 2000);
 						   }
 					  }
     		  });
     },

     
     

});
