/**
 * Created by attila on 6/3/16.
 */

var MouseListener = easejs.Interface('MouseListener', {

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * @param eventData
     * @param handler
     */
    'public mouseClicked': ['eventData', 'handler'],


    /**
     * Invoked when the mouse enters a component.
     * @param eventData
     * @param handler
     */
    'public mouseEntered': ['eventData', 'handler'],


    /**
     * Invoked when the mouse exits a component.
     * @param eventData
     * @param handler
     */
    'public mouseExit': ['eventData', 'handler'],


    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * @param eventData
     * @param handler
     */
    'public mouseMoved': ['eventData', 'handler'],


    /**
     * Invoked when a mouse button has been released on a component.
     * @param eventData
     * @param handler
     */
    'public mouseReleased': ['eventData', 'handler'],

    /**
     * Sets the AbstractBaseUI element
     */
    'public setElement': ['element']

});