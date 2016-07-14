/**
 * Created by attila on 5/19/16.
 */

IColumn.prototype = Object.create(AbstractBaseUI.prototype);
IColumn.prototype.constructor = IColumn;

/**
 * A Column form ITable
 * @constructor
 */
function IColumn() {
    AbstractBaseUI.call(this, UICore.JQ("<td></td>"));
};


/**
 * Sets the text of the current component
 * @param text
 */
IColumn.prototype.setText = function (text) {
    if (text instanceof String) {
        this.element.html(text);
    } else {
        this.element.append(text);
    }
};