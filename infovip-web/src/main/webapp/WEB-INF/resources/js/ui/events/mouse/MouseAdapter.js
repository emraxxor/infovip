/**
 * Created by attila on 5/20/16.
 */

var MouseAdapter = easejs.Class('MouseAdapter').implement(MouseListener).extend({

    /**
     * @type {AbstractBaseUI}
     */
    'private element': null,

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * @param eventData
     * @param handler
     */
    'virtual public mouseClicked': function (eventData, handler) {
        throw new Error("Not implemented yet.");
    },


    /**
     * Invoked when the mouse enters a component.
     * @param eventData
     * @param handler
     */
    'virtual public mouseEntered': function (eventData, handler) {
        throw new Error("Not implemented yet.");
    },


    /**
     * Invoked when the mouse exits a component.
     * @param eventData
     * @param handler
     */
    'virtual public mouseExit': function (eventData, handler) {
        throw new Error("Not implemented yet.");
    },


    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * @param eventData
     * @param handler
     */
    'virtual public mouseMoved': function (eventData, handler) {
        throw new Error("Not implemented yet.");
    },


    /**
     * Invoked when a mouse button has been released on a component.
     * @param eventData
     * @param handler
     */
    'virtual public mouseReleased': function (eventData, handler) {
        throw new Error("Not implemented yet.");
    },


    /**
     *
     * @param {AbstractBaseUI} element
     */
    'virtual public setElement': function (element) {
        this.element = element;
        this.element.getElement().click(this.mouseClicked);
        this.element.getElement().mouseenter(this.mouseEntered);
        this.element.getElement().mouseleave(this.mouseExit);
        this.element.getElement().mousemove(this.mouseExit);
        this.element.getElement().mouseup(this.mouseReleased);
    }
});









