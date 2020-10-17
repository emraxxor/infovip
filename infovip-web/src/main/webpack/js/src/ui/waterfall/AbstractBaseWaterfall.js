/**
 * Simple wrapper for the @BaseWaterfall
 * 
 * @author Attila Barna
 */
export default class AbstractBasewaterfall extends BaseWaterfall {


    constructor(component, options = {}) {
        super(component.waterfallId);
        this.component = component;
        this.waterfallElement = component.$el;
        this.options = {
            itemCls: 'item-waterfall', 
    		prefix: 'waterfall',
    		fitWidth: true, 
    		colWidth: 180, 
    		gutterWidth: 10,
    		gutterHeight: 10,
    		align: 'center',
    		minCol: 1, 
    		maxCol: undefined, 
    		maxPage: undefined, 
    		containerStyle: {position: 'relative'},
    		resizable: true, 
    		isFadeIn: true,
    		isAnimated: true,
    		animationOptions: {},
            path: e =>  this.onLoad(e),
    		isAutoPrefill: true,
    		checkImagesLoaded: true,
            dataType: 'json', 
            loadingMsg: '<div style="text-align:center;padding:10px 0; color:#999;"><img src="data:image/gif;base64,R0lGODlhEAALAPQAAP///zMzM+Li4tra2u7u7jk5OTMzM1hYWJubm4CAgMjIyE9PT29vb6KiooODg8vLy1JSUjc3N3Jycuvr6+Dg4Pb29mBgYOPj4/X19cXFxbOzs9XV1fHx8TMzMzMzMzMzMyH5BAkLAAAAIf4aQ3JlYXRlZCB3aXRoIGFqYXhsb2FkLmluZm8AIf8LTkVUU0NBUEUyLjADAQAAACwAAAAAEAALAAAFLSAgjmRpnqSgCuLKAq5AEIM4zDVw03ve27ifDgfkEYe04kDIDC5zrtYKRa2WQgAh+QQJCwAAACwAAAAAEAALAAAFJGBhGAVgnqhpHIeRvsDawqns0qeN5+y967tYLyicBYE7EYkYAgAh+QQJCwAAACwAAAAAEAALAAAFNiAgjothLOOIJAkiGgxjpGKiKMkbz7SN6zIawJcDwIK9W/HISxGBzdHTuBNOmcJVCyoUlk7CEAAh+QQJCwAAACwAAAAAEAALAAAFNSAgjqQIRRFUAo3jNGIkSdHqPI8Tz3V55zuaDacDyIQ+YrBH+hWPzJFzOQQaeavWi7oqnVIhACH5BAkLAAAALAAAAAAQAAsAAAUyICCOZGme1rJY5kRRk7hI0mJSVUXJtF3iOl7tltsBZsNfUegjAY3I5sgFY55KqdX1GgIAIfkECQsAAAAsAAAAABAACwAABTcgII5kaZ4kcV2EqLJipmnZhWGXaOOitm2aXQ4g7P2Ct2ER4AMul00kj5g0Al8tADY2y6C+4FIIACH5BAkLAAAALAAAAAAQAAsAAAUvICCOZGme5ERRk6iy7qpyHCVStA3gNa/7txxwlwv2isSacYUc+l4tADQGQ1mvpBAAIfkECQsAAAAsAAAAABAACwAABS8gII5kaZ7kRFGTqLLuqnIcJVK0DeA1r/u3HHCXC/aKxJpxhRz6Xi0ANAZDWa+kEAA7" alt=""><br />Loading...</div>',
    		state: { 
    			isDuringAjax: false, 
    			isProcessingData: false, 
    			isResizing: true,
    			curPage: 1 
    		},
            callbacks: {
    			loadingStart: (e) =>  e.show(),
    			loadingFinished: (e,f) => this.loadingFinished(e,f),
    			loadingError: this.loadingError,
    			renderData: (d,t) => this.render(d,t)
            }
        };


        Object.keys(options).forEach( (v) => this.options[v] = options[v] );

    }

    onLoad(page) {

    }

    onScroll(token) {
        this.setScroll(token);
    }


    loadingFinished(loading, isBeyondMaxPage) {
        if ( !isBeyondMaxPage ) {
            loading.fadeOut();
            this.onCreationComplete();
        } else {
            loading.remove();
        }
    }

    setMessage(msg) {
        jQuery('#waterfall-message').html(msg);
    }

    render(data, dataType) {}

    loadingError(message, xhr) {
        message.html('Data load faild, please try again later.');
    }

    display() {
       this.waterfall = jQuery(this.waterfallElement).waterfall(this.options);
    }


}