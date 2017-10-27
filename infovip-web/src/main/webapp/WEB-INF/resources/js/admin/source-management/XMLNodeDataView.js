var XMLNodeDataView = easejs.Class('XMLNodeDataView').extend(Controller,{
	
	'private nodeView' : null,

	'private xmlNodeData' : null,
	
	'private xmlDataMarker' : null,
	
	'private form' : null,
	
	'private xmlDataLayout' : null,
	
	'private xmlDataTable' : null,
	
	'private xmlScrollBar' : null,
	
	'public static SUBMIT_URL' : ADMIN_PATH + '/source-management/submit',
	
	'private parent' : null,
	
	'private sourceId' : null,
	
	'virtual override __construct' : function(parent) {
		this.parent = parent;
		this.xmlNodeData = parent.getXMLNodeData();
		this.xmlDataMarker = parent.getXMLDataMarker();
		this.sourceId = parent.getSourceId();
		this.xmlDataLayout = webix.ui({});
		this.form = webix.ui({
			width : 800,
			view : "form",
			elements : [],
		});
		
		this.xmlDataTable = webix.ui({
			view:"datatable",
			columns: [],
			autowidth:true, 
			autoheight:true,
			tooltip: true,
			scroll : "x",
			data: []
		});	
		
		this.xmlScrollBar = webix.ui({  view:"scrollview", height : 100, scroll: "x", body: { rows : [] } });
	},

	
	'public virtual createView' : function() {
		var that = this;
		var xmlDataColumns = this.xmlDataTable.config.columns;
		var values = jQuery.map(this.xmlNodeData.nodeNames,function(v,k){return k;});
		
		for(var key in this.xmlDataMarker.data ) {
			var data = { view:"select",  name: key, labelAlign: "left", labelWidth: 200, label: this.xmlDataMarker.data[key] , options: values } ;

			if ( this.xmlDataMarker.required.indexOf(key) != -1 ) {
				data['required'] = true;
				data['validate'] = function(v) {
					return v != '';
				}
			}
			
			this.form.addView( webix.ui( data) );
		}
		
		jQuery.each(this.xmlNodeData.nodeNames,function(k,v){
			xmlDataColumns.push({
				id : k,
				header : k
			});
		});
		this.xmlDataTable.refreshColumns();
		this.xmlDataTable.add(this.xmlNodeData.nodeNames);
		this.form.addView( this.xmlScrollBar );
		this.xmlScrollBar.getBody().addView(this.xmlDataTable);
		this.form.addView(webix.ui({ view:"button", label: __tr("admin.submit") , on : { "onItemClick" : function(id,e) { that.onSubmit(id,e); } } }))
		return this;
	},
	
	'public onSubmit' : function(id,e) {
		if ( this.form.validate() ) {
			 var resp = this.load( XMLNodeDataView.$('SUBMIT_URL') ,  jQuery.extend( {sourceId:this.sourceId} , this.form.getValues() ) );
			 if ( resp.code != undefined && resp.code == ApplicationScope.response.Code.ERROR  ) {
				 this.parent.close();
			 } else {
				 DefaultErrorHandler(__tr("admin.msg.error.save")).display();
			 }
		}
	},
	
	'public getView' : function() {
		return this.form;
	}
	
});
