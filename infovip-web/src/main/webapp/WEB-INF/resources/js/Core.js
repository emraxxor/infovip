DependencyLoader
.create()
.js('js/core/IController')
.js('js/core/Controller')
.js('js/core/user/CurrentUser')
.js('js/theme/DefaultTheme')
.import(function(p){
	    __tr = Translator.tr;
		Translator.initialize(Controller());
		DefaultTheme.create().template();
},{});