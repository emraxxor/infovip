/**
 * Created by attila on 5/19/16.
 */

ITable.prototype = Object.create(AbstractBaseUI.prototype);
ITable.prototype.constructor = ITable;

/**
 * Table generator component
 * @constructor
 */
function ITable() {

    /**
     * Current table
     * @type {*|jQuery|HTMLElement}
     */
    this.table = UICore.JQ("<table></table>");
}


/**
 * Appends a new row to the table
 * @param {IRow} row
 */
ITable.prototype.addRow = function (row) {
    this.table.append(row.getElement());
};