/**
 *  @author Attila Barna
 */
var CurrencyFormatter = easejs.Class('CurrencyFormatter').extend( {
	
	'public static DEFAULT_CURRENCY' : 'HUF',
	
	'private currencyFormat' : null,
	
	'private locale' : null,
	
	'private value' : null,
	
    'virtual __construct': function (currencyFormat) {
    	this.currencyFormat = currencyFormat;
    	this.locale = navigator.language;
     },
     
    'public virtual format' : function(value) {
    	return new Intl.NumberFormat(this.locale, { style: 'currency', currency: this.currencyFormat }).format(value);
     }
    
});
