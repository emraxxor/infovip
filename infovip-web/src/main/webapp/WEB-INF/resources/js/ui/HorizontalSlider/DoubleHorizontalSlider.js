/**
 * Created by attila.
 */


DoubleHorizontalSlider.prototype = Object.create(AbstractBaseUI.prototype);
DoubleHorizontalSlider.prototype.constructor = DoubleHorizontalSlider;


/**
 * A primitive double horizontal slider component
 * @constructor
 */
function DoubleHorizontalSlider() {

    /**
     * Top scrollbar element
     * @type {IPanel}
     */
    this.topScrollbar = new IPanel("top-scrollbar-panel");

    /**
     *
     * @type {IPanel}
     */
    this.topSlider = new IPanel("top-scrollbar-slider");



}


/**
 * Wraps the specified element to the current element
 * @param ob
 */
DoubleHorizontalSlider.prototype.wrapElement = function (ob) {

};