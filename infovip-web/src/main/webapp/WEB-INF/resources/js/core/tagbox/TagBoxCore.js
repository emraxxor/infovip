/**
 * @author Attila Barna
 */
jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/tagbox/TagBox')
        .css('js/core/tagbox/TagBox')
        .import();
}); 