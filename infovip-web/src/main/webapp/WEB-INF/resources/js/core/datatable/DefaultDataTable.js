/**
 * The default datatable 
 */
var DefaultDataTable = easejs.Class('DefaultDataTable').extend(Controller,{

	'protected dataTable' : null,
	
	'private datatableColumns' : null,
	
	'private dataTableOptions' : {},
	
	'protected htmlDataTable' : null,

	
    'override virtual __construct' : function (columns,options) {
        this.__super();
        this.datatableColumns = columns;
        this.dataTableOptions = options == undefined ? {} : options;
        this.htmlDataTable = jQuery("<table></table>",{class : 'display',width:'100%'});
    },
    
    'public getHTMLDataTable' : function() {
    	return this.htmlDataTable;
    },
    
    
    'public setColumnListener' : function(val) {
    	this.dataTableOptions['columnListener'] =  val;
    	return this;
    },
    
    
    'public getDataTable' : function() {
    	return this.dataTable;
    },
    
    'public setAjaxSourceURL' : function(val) {
    	this.dataTableOptions['ajaxSource'] = val;
    	return this;
     },
    
    'public setInitComplete' : function(val) {
    	this.dataTableOptions['initComplete'] = val;
    	return this;
    },
     
    'public setPageLength' : function(val) {
    	this.dataTableOptions['pageLength'] = val;
    	return this;
    },
    
    'public setDrawCallBack' : function(val) {
    	this.dataTableOptions['drawCallback'] = val;
    	return this;
    },
    
    'public setBeforeAjaxSend' : function(val) {
    	this.dataTableOptions['beforeAjaxSend'] = val;
    	return this;
    },
    
    'public create' : function() {
		var that = this;
		this.dataTable = this.htmlDataTable.DataTable({
	        "dom": '<"dataTableButtonsContainer1"Bl>frpitip<"clearfix">',
			"displayStart": 0,
	        "stateSave"        : true,
			"pageLength": (this.dataTableOptions.pageLength != undefined ? this.dataTableOptions.pageLength : 100),
	        "sorting"         : [[0, 'asc']],
	        "lengthMenu"      : [[10, 25, 50, 100, 500, 1000, -1], [10, 25, 50, 100, 500, 1000, "All"]],
	        "processing"       : true,
	        "serverSide"       : true,
	        "columns"         : this.datatableColumns,
	        "ajaxSource"       : this.dataTableOptions.ajaxSource,
	        "initComplete"	: function(settings,json) {
	        	that.refreshEventListeners();
	        	if ( that.dataTableOptions.initComplete != undefined ) {
	        		that.dataTableOptions.initComplete(settings,json);
	        	}
	        },
	        "fnServerParams": function ( aoData ) {
	        	if ( that.dataTableOptions.beforeAjaxSend != undefined ) {
	        		that.dataTableOptions.beforeAjaxSend(aoData);
	        	}
	        },
	        "drawCallback": function( settings ) {
	        	if ( that.dataTableOptions.drawCallback != undefined ) {
	        		that.dataTableOptions.drawCallback(settings);
	        	}
	        	that.refreshEventListeners();
	        },
	        "buttons": [  {
		           extend: "colvis",
		           text: "Columns"
		        }, 'csv', 'excel', 'pdf', 'print' ]
		});
    },
   
    'public reload' : function() {
    	this.reloadDataTable();
    },
    
    'public virtual reloadDataTable' : function() {
		var that = this;
		this.dataTable.ajax.reload(function () {
			that.refreshEventListeners();
		}, false);
    },
    
   'public virtual refreshEventListeners' : function()  {
	   if ( this.dataTableOptions.columnListener != undefined ) {
		   this.dataTableOptions.columnListener(this.htmlDataTable,this);
	   }
    },
    
    'public appendTo' : function(element) {
    	element.append(this.htmlDataTable);
    }
	
});
