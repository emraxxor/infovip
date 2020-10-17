/**
 * @author Attila Barna 
 */
class WebpackLazyModuleLoader extends Module {
	
	constructor() {
		super();
		this.dependency('js/core/IController');
		this.dependency('js/core/Controller');
		this.dependency('js/core/user/CurrentUser');
		this.dependency('js/theme/DefaultTheme');
		this.dependency('js/core/search/BaseWaterfall');
	}
	
	run() {
		DependencyLoader.WebpackComponent('module');
	    document.querySelectorAll("[vue-component='dependency']").forEach(e => new Vue({el:e}) );		
	}
}
