/**
 * @author Attila Barna
 */
var TinyMceManager = easejs.Class('TinyMceManager').extend(Controller,{
	
	'public static HANDLER' : {
	     USER_IMAGE : ApplicationScope.API.USER.IMAGE_UPLOAD_HANDLER	 
	 },
	
    'override virtual __construct' : function () {
    	this.__super();
     },
     
    'public create' : function() {
    	return jQuery('<textarea  id="'+guidGenerator()+'"></textarea>');
    },
    
    
    'public basic' : function(textarea,width,height) {
    	
    	if ( width == undefined )
    		width = '100%';
    	
    	if ( height == undefined ) 
    		height = '300';
    	
    	tinymce.init({
    				  selector : '#' + textarea.attr('id'),
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
    
    'public full' : function(textarea,width,height) {
    	
    	if ( width == undefined )
    		width = '100%';
    	
    	if ( height == undefined ) 
    		height = '300';
    	
    	tinymce.init({
    				  selector : '#' + textarea.attr('id'),
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
    }
});
