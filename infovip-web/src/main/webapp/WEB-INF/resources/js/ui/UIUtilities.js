/**
 * Created by attila.
 */


/**
 * Contains some utility functions
 * @todo
 * Expand the documentation
 *
 * @constructor
 *
 * @author Attila Barna
 */
function UIUtilities() {

}

/**
 * Causes doRun.run() to be executed if the document is already loaded or can be used
 * window.setTimeout(func, [delay, param1, param2, ...]) function too
 * @param {Runnable} doRun (final)
 */
UIUtilities.invokeDocumentReady = function (doRun) {
    if (doRun instanceof Runnable) {
        UICore.JQ(document).ready(function () {
            doRun.run();
        });
    }
};