/**
 *  @author attila
 */
jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/search/NavbarInterface')
        .js('js/core/search/BaseNavBar')
        .js('js/core/search/BaseWaterfall')
        .js('js/core/search/DefaultNavBar')
        .js('js/core/search/DefaultWaterfall')
        .import(function(args){
        	var dnb = new DefaultNavBar(new DefaultWaterfall());
        	dnb.display();
        },{});
}); 