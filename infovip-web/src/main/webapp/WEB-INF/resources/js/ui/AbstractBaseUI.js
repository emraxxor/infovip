/**
 * Created by attila.
 */

AbstractBaseUI.prototype.constructor = AbstractBaseUI;

/**
 *
 * AbstractBaseUI for the UI components
 *
 * Most of the UI element must to be extended from this class
 *
 * @param {JQuery} element
 * Reference for the object
 *
 * @constructor
 */
function AbstractBaseUI(element) {

    /**
     *
     * @type {JQuery}
     */
    this.element = element;

    /**
     *
     * @type {JQuery}
     */
    this.parentElement = null;

}


/**
 * Appends the specified object to the current element
 * @param style
 */
AbstractBaseUI.prototype.append = function (ob) {
    this.element.append(ob);
};


/**
 * Cause javascript doesn't support subclasses so I had to define RunnableParent here
 * @type {Runnable}
 */
RunnableParent.prototype = Object.create(Runnable.prototype);

RunnableParent.prototype.constructor = RunnableParent;

function RunnableParent(baseUI, jqVal) {

    /**
     * @type {AbstractBaseUI}
     */
    this.baseUI = baseUI;

    /**
     * @type {String}
     */
    this.jqVal = jqVal;
};

RunnableParent.prototype.run = function () {
    this.baseUI.parentElement = UICore.JQ(this.jqVal);
    this.baseUI.appendTo(this.baseUI.parentElement);
    if (this.baseUI.parentElement.length == 0) {
        throw new Error("Specified ID : {0} doesn't exits!!!!".format(this.jqVal));
    }
};

/**
 * Sets the parent element of the current element
 * Need to know , this method is invoked synchronously and it waits
 * til the document is not loaded if the specified val doesn't exists
 *
 * @param string val
 * @constructor
 */
AbstractBaseUI.prototype.setParentElement = function (val) {
    if (UICore.JQ(val).length == 0) {
        UIUtilities.invokeDocumentReady(new RunnableParent(this, val));
    } else {
        this.parentElement = UICore.JQ(val);
    }
};

/**
 * Dislays the current element
 * @param val
 */
AbstractBaseUI.prototype.setVisible = function (val) {
    if (val == true) {
        this.element.show();
    } else {
        this.element.hide();
    }
};


/**
 * Appends the current object to the specified object
 *
 *
 * @param JQuery ob
 * @constructor
 */
AbstractBaseUI.prototype.appendTo = function (ob) {
    this.element.appendTo(ob);
};

/**
 * Sets the css of the current element
 * @param style
 */
AbstractBaseUI.prototype.css = function (css) {
    this.element.css(style);
};

/**
 * Sets the style of the current element
 * @param style
 */
AbstractBaseUI.prototype.setStyle = function (style) {
    this.set('style', style);
};


/**
 * Sets the height of the current element
 * @param width
 */
AbstractBaseUI.prototype.setHeight = function (width) {
    this.set('height', width);
};


/**
 * Sets the height of the current element
 * @param width
 */
AbstractBaseUI.prototype.setWidth = function (width) {
    this.set('width', width);
};

/**
 * Sets the vlaue of the specified attribute
 * @param attr
 * @param val
 */
AbstractBaseUI.prototype.set = function (attr, val) {
    this.element.attr(attr, val);
};

/**
 * Gets the specified attribute
 * @param attr
 * @param style
 */
AbstractBaseUI.prototype.get = function (attr) {
    return this.element.attr(attr);
};


/**
 * Gets the current element
 * @return {JQuery}
 */
AbstractBaseUI.prototype.getElement = function () {
    return this.element;
};


/**
 * Sets the id of the current element
 * @return {JQuery}
 */
AbstractBaseUI.prototype.setID = function (id) {
    this.set("id", id);
};

/**
 * Wraps the current element to the ob
 * @param ob
 */
AbstractBaseUI.prototype.wrap = function (ob) {
    this.element.wrap(ob);
};


/**
 * Adds a new event listener to the current element
 * @param eListener
 */
AbstractBaseUI.prototype.addEventListener = function (eListener) {
    if (eListener.isA(MouseListener)) {
        eListener.setElement(this);
    } else {
        UILogger.error("Not supported element");
        UILogger.error(eListener);
        throw new Error("Not supported element : {0} ".format(eListener));
    }
};

/**
 * Gets the htmlNode of the current element
 * @return {*|jQuery}
 */
AbstractBaseUI.prototype.htmlNode = function () {
    return $("<div></div>").append(this.element).html();
};

/**
 * Sets the class of the current element
 */
AbstractBaseUI.prototype.setClass = function (val) {
    this.set('class', val);
};

/**
 * Adds a new class to the current element
 * @param val
 */
AbstractBaseUI.prototype.addClass = function (val) {
    if (this.get('class') != undefined) {
        this.set('class', this.get('class') + ' ' + val);
    } else {
        this.set('class', val);
    }
}
