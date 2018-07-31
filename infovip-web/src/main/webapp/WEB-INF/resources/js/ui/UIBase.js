/**
 * Created by attila on 5/20/16.
 *
 * This file is intended to contains functions and its prototypes which 
 * are should be used by any component of UI.
 *
 * @author Attila Barna
 */


/**
 * Add a format method to the String object
 */
if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}



