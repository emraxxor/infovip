/**
 * Created by attila.
 */
UICore.prototype.constructor = UICore;

/**
 * Cause I really don't like hardcoded strings in the code therefore I started to create
 * a primitive ui library to make the code more prettier and clearer.and more readable for anyone.
 *
 * Basically, UICore is not part of the logness, I started to develop it for creating simple and understandable
 * code and I hope once time I will publish it on github if it will have been done.
 *
 * UICore requires a few module has already been loaded before starting using it, notably the following :
 * - UIBase
 * - UILogger
 *
 * External libraries :
 *  - JQuery
 *
 * The order is important because UILogger depends on UIBase
 *
 * @author Attila Barna Barna
 * @constructor
 * @package icollections.infovip
 */
function UICore() {

    this.core = [
        'events/Listener',
        'events/mouse/MouseListener',
        'core/Runnable',
        'AbstractBaseUI',
        'button/IButton',
        'HorizontalSlider/DoubleHorizontalSlider',
        'panel/IPanel',
        'table/IColumn',
        'table/IRow',
        'table/ITable',
        'UIUtilities',
        'events/mouse/MouseAdapter',
        'events/mouse/DefaultMouseAdapter',
    ];
}

/**
 * Reference of the webix object
 * @type {*|webix}
 */
UICore.webix = webix;

/**
 * Path of the library
 * It is optimized for the current project
 * @type {string}
 */
UICore.PATH = "js/ui";


/**
 * Alias of jQuery
 * @type {*|jQuery}
 */
UICore.JQ = jQuery;


/**
 * Loads the core elements of the current library
 */
UICore.initialize = function () {
    var uiCore = new UICore();
    uiCore.load(uiCore.core);
    UILogger.notice("UICore has been successfully initialized.");
};


UICore.prototype.load = function (elements) {
    for (var i = 0; i < elements.length; i++) {
        UICore.JQ.ajax({
            url: UICore.PATH + '/' + elements[i] + '.js',
            dataType: "script",
            async: false,
            success: function () {
                UILogger.notice(" {0} has been imported ".format(elements[i]));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                UILogger.error(jqXHR);
                UILogger.error(textStatus);
                UILogger.error(errorThrown);
                throw new Error("Following element could not be loaded {0} ".format(elements[i]));
            }
        });
    }
};