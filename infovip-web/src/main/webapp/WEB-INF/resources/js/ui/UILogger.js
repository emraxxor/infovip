/**
 * Created by attila.
 */

/**
 * A primitive logger for UI and its components, it must be loaded before UICore would be instantiateds
 * @constructor
 */
function UILogger() {

}

/**
 *  Logs the error messages
 */
UILogger.error = function (e) {
    console.error(e);
};


/**
 * Logs notices
 * @param e
 */
UILogger.notice = function (e) {
    console.log(e);
};


/**
 * Logs warn messages
 * @param e
 */
UILogger.warn = function (e) {
    console.warn(e);
};

/**
 * Logs debug messages
 * @param e
 */
UILogger.debug = function (e) {
    console.debug(e);
};