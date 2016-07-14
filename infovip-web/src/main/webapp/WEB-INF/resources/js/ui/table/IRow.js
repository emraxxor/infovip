/**
 * Created by attila on 5/19/16.
 */

IRow.prototype = Object.create(AbstractBaseUI.prototype);
IRow.prototype.constructor = IRow;


/**
 * A row for ITable
 * @constructor
 */
function IRow() {
    /**
     * Current row
     * @type {*|jQuery|HTMLElement}
     */
    this.row = UICore.JQ("<tr></tr>");

    AbstractBaseUI.call(this, this.row);
}

/**
 * Appends a new column to the current row
 * @param {IColumn} column
 */
IRow.prototype.addColumn = function (column) {
    this.row.append(column.getElement());
};

