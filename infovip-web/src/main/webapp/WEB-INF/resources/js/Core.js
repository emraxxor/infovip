jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/IController')
        .js('js/core/Controller')
        .import();
});