/**
 * Default directives for administration interface
 * @author Attila Barna
 */


var ADMIN_DEFAULT_SIDEBAR_ID = "admin:admin-default-sidebar";
var ADMIN_BODY_PANEL = "admin:default:admin-body-content";
var ADMIN_PATH = ApplicationScope.config.WEB_ROOT + '/' + 'admin';
var ADMIN_DEFAULT_LAYOUT_BODY = "admin:default:body.default.layout";

webix.protoUI({name : "cdatatable",}, webix.ui.datatable, webix.ActiveContent);
webix.protoUI({name : "ctreetable",}, webix.ui.treetable, webix.ActiveContent);



webix.editors.defaultParameterEditor = {
	
		focus : function() {
			this.getInputNode(this.node).focus();
			this.getInputNode(this.node).select();
		},
		getValue : function() {
			return this.getInputNode(this.node).value;
		},
		setValue : function(value) {
				var input = this.getInputNode(this.node);
				input.value = value;
		},
		getInputNode : function() {
				return this.node.firstChild;
		},
		render : function() {
			return webix.html.create("div", {
				"class" : "webix_dt_editor"
			}, "<input type='text'>");
		},
};


webix.protoUI({
	   name:"coloredButton", 
	   $init:function(config){ 
		    this.ctype = config.ctype;
	        this.cicon = config.cicon;
	        this.cid   = config.cid;
	        this.ciconcolor   = config.ciconcolor;
	   },
	   $cssName:"button",
	   defaults:{
		  ciconcolor: "#fff",
	      css: "",
	      on:{  
	            'onItemClick' : function(id, e) {  },
	            'onBeforeRender' : function(o) {    },
	            'onAfterRender' : function(o) {
	            	jQuery(this.getInputNode()).attr('id', this.cid);
	            	//jQuery(this.getInputNode()).removeClass('webixtype_base');
	            	jQuery(this.getInputNode()).addClass(this.ctype);
	            	jQuery(this.getInputNode()).prepend( jQuery( "<span></span>", { class: this.cicon, style: "color: "+this.ciconcolor } ) );
	            	
	            }
	      }
	   },          
	   custom_func:function(){ }, 
}, webix.ui.button);

webix.protoUI({
	  name:"imageUploader",
	  $init:function(config){
		  this.imageFieldName=config.imageFieldName;
		  this.formElementName=config.formElementName;
		  this.canvasWidth = config.canvasWidth == undefined ? 320 : config.canvasWidth;
		  this.canvasHeight = config.canvasHeight == undefined ? 110 : config.canvasHeight;
      },
      defaults:{
	      on:{
	          'onAfterFileAdd':function(item){
	          	var reader = new FileReader();
	          	var img = new Image();
	          	var resizedImg = new Image();
	          	reader.readAsDataURL(item.file); 
	          	reader.addEventListener("load", function () {
	              	
	          		img.onload = function(){
	          			var canvas = document.createElement("canvas"),
	          	        ctx = canvas.getContext("2d");
	          			
	          			canvas.width = this.canvasWidth;
	          	        canvas.height= this.canvasHeight;
	          			var canvasRatio = canvas.width/canvas.height;
	          			var originalImageRatio = img.width/img.height;
	          			
	          			if(originalImageRatio < canvasRatio) {
	          				newImageWidth = (canvas.height*img.width)/img.height;
	          				ctx.drawImage(img, 0, 0, img.width, img.height, (canvas.width-newImageWidth)/2 , 0 ,newImageWidth, canvas.height);
	          			} else {
	          				newImageHeight = (canvas.width*img.height)/img.width;
	          				ctx.drawImage(img, 0, 0, img.width, img.height, 0 , (canvas.height-newImageHeight)/2 ,canvas.width, newImageHeight);
	          			}
	          			
	          	        reader.result.substring(reader.result.indexOf(',')+1);
	          	        
	          	        $$(this.imageFieldName).setValues(canvas.toDataURL().substring(canvas.toDataURL().indexOf(',')+1));
		                $$(this.imageFieldName).refresh();
		                $$(this.formElementName).setValues({[this.imageFieldName]: canvas.toDataURL().substring(canvas.toDataURL().indexOf(',')+1)},true);
	          		}.bind(this);
	
	          		img.src = reader.result;
	      	    }.bind(this), false);
	          	return true;
	          }
	      }
      }
}, webix.ui.uploader);

webix.protoUI({
	  name:"image",
	  defaults: {
		label: '',
		labelWidth: 85,
		src: '',
		alt: '',
	  },
	  $init:function(config){
		  this.src=config.src;
		  this.label=config.label;
		  this.alt=config.alt;
		  this.labelWidth=config.labelWidth;
		  this.imageFieldName=config.imageFieldName;
		  this.formElementName=config.formElementName;
	  },
	  getValue:function(){
		return this.src;
	  },
	  setValue:function(value){
		if ( value == "" || value == null || value.length < 5 )
			  return;
		  
		this.src=value;
		this.$view.innerHTML = "<div class=\"webix_el_box\"><label class=\"webix_inp_label\" style=\"width: "+this.labelWidth+"px\">"+this.label+"</label><div style=\"float: left; border-width: 1px; border-style: solid; border-color: #cfddda\"><img alt=\""+this.alt+"\" src=\"data:image/png;base64,"+this.src+"\"/></div>";
	  },
	  template: ""
}, webix.ui.template);	

Document.height = function() {
	return jQuery(document).height();
}

Document.width = function() {
	return jQuery(document).width();
}

Document.window = {
		
	width: function() {
		return jQuery(window).width();
	},

	height : function() {
		return jQuery(window).height();
	},
	
	preferredSize :  function() { 
		return {
			width: (Document.window.width() * 0.90 ),
			height: (Document.window.height() * 0.90 )  
		}
	}
}