jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/dialog/WindowCore')
        .js('js/test/core/dialog/DefaultDialogWindowTest/DefaultDialogWindowTest')
        .import(function(param){
        	setTimeout(function(){ 
        		DefaultDialogWindowTest().create().display({"width":"90%"});
        	},1000);
        },{});

    
});
