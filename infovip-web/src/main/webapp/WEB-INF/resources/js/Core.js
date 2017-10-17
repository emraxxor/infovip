DependencyLoader
.create()
.js('js/core/IController')
.js('js/core/Controller')
.import(function(p){
	    __tr = Translator.tr;
		Translator.initialize(Controller());
},{});