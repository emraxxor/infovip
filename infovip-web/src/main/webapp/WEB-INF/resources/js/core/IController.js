/**
 * Created by attila on 1/3/17.
 */
/**
 * The main interface that is required for each component that communicates with the server.
 */
var IController = easejs.Interface('IController',{

    /**
     * Calls the specified url on the local server then parses its response
     */
    'public load' : ['purl', 'pdata', 'methodType', 'ajaxGlobal'],

    /**
     * Calls the specified url, the response will not be parsed
     */
    'public execute' : ['purl','pdata']

});