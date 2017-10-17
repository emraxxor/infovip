/**
 *  @author attila
 */
var BaseNavBar = easejs.Class('BaseNavBar').implement(NavbarInterface).extend(Controller,{

	'protected id' : null,
	
	'protected settings' : {},
	
	'protected waterfall' : null,
	
    'override virtual __construct': function (waterfall) {
    	this.__super();
    	var that = this;
    	this.waterfall = waterfall;
    	this.settings = {
                // these are required:
                suggestUrl: 'search/suggester?term=', // the URL that provides the data for the suggest
                ivfImagePath: '', // the base path for instant visual feedback images
                instantVisualFeedback: 'all', // where the instant visual feedback should be shown, 'top', 'bottom', 'all', or 'none', default: 'all'
                throttleTime: 100, // the number of milliseconds before the suggest is triggered after finished input, default: 300ms
                extraHtml: undefined, // extra HTML code that is shown in each search suggest
                highlight: true, // whether matched words should be highlighted, default: true
                queryVisualizationHeadline: '', // A headline for the image visualization, default: empty
                animationSpeed: 200, // speed of the animations, default: 300ms
                enterCallback: function(text,link){ 
                	that.onSubmit(text);
                }, // callback on what should happen when enter is pressed, default: undefined, meaning the link will be followed
                enterCallbackResult: function(text,link){
                	that.onSubmit(text);
                }, // callback on what should happen when enter is pressed on a result or a suggest is clicked
                placeholder: 'Search for something',
                minChars: 2, // minimum number of characters before the suggests shows, default: 3
                suggestOrder: [], // the order of the suggests
                suggestSelectionOrder: [], // the order of how they should be selected
                noSuggests: '<b>We haven\'t found anything for you, <u>sooorrryyy</u></b>',
                maxWidth: 400 // the maximum width of the suggest box, default: as wide as the input box
       };
    },
    
    'public onSubmit' : function(text) {
    	this.waterfall.setTerm(text);
    	this.waterfall.getWaterfall().waterfall('removeAll',function() { });
    	this.waterfall.getWaterfall().waterfall('reload',function() { });
    	this.waterfall.display();
    },
    
    'public setWaterfall' : function(val) {
    	this.waterfall = val;
    },
    
    'public getWaterfall' : function() {
    	return this.waterfall;
    },
    
    'public virtual display' : function() {
    	jQuery('#' + this.id).unibox(this.settings);
    	this.waterfall.display();
    },
    
    'public setId' : function(val) {
    	this.id = val;
    }

	
});