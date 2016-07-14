/**
 * Created by attila.
 */

IPanel.prototype = Object.create(AbstractBaseUI.prototype);
IPanel.prototype.constructor = IPanel;


/**
 * Double horizontal slider component
 * @constructor
 */
function IPanel(id) {

    AbstractBaseUI.call(this, UICore.JQ("<div></div>"));

    this.setID(id);


    this.setText = function (text) {
        if (text instanceof String) {
            this.element.html(text);
        } else {
            this.append(text);
        }
    }

};






