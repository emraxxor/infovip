/**
 * Basic scroll implementation for infovip
 * @author Attila Barna
 */
var IScroll = easejs.Class('IScroll').extend(UIController,{
	
	'protected node' : null,
	
	'protected scrollId' : null,
	
	'override virtual __construct' : function(node) {
		this.node = node;
	 },
	
	'public virtual onScrollFire' : function() {},
	
	 
	'public getScrollId' : function() {
		return this.scrollId;
	 },
	 
	'public getNode' : function() {
		return this.node;
	},
	 
	'public setScrollId' : function(scrollid) {
		this.scrollId = scrollid;
	},

    'public override virtual onCreationComplete' : function() {
        const that = this;
        const node = this.node;
        jQuery(window).scroll( () => {
        	if (  ( window.scrollY ) > node[0].getClientRects()[0].height  ) {
            //if(jQuery(window).scrollTop() >= node.offset().top + node.outerHeight() - window.innerHeight) {
                that.onScrollFire();
            //}
        	}
        });
	}
	
});
