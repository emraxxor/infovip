/**
 *  @author Attila Barna
 */
var GeneralSettingsValidator = easejs.Class('GeneralSettingsValidator').extend(DefaultHTMLFormValidator,{
	
	'override virtual __construct' : function(form) {
		this.__super( form );
		
		this.setRules( [ 
			{
				field : 'userMail',
				isValid : function(value) {
				    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				    return re.test(String(value).toLowerCase());
				},
				validate : function(o) {
					o.removeClass(this.field,'warn-box');
					o.removeMessage(this.field);
					
					if ( !this.isValid(o.getField(this.field).value) ) {
						o.addClass(this.field,'warn-box');
						o.addMsg(this.field,__tr('Invalid email'));
						return false;
					}
					return true;
				}
			},
			{
				field : 'userPassword',
				score : function(pass) {
					var score = 0;
					if (!pass)
						return score;

					// award every unique letter until 5 repetitions
					var letters = new Object();
					for (var i=0; i<pass.length; i++) {
						letters[pass[i]] = (letters[pass[i]] || 0) + 1;
						score += 5.0 / letters[pass[i]];
					}

					// bonus points for mixing it up
					var variations = {
						digits: /\d/.test(pass),
						lower: /[a-z]/.test(pass),
						upper: /[A-Z]/.test(pass),
						nonWords: /\W/.test(pass),
					}

					variationCount = 0;
					for (var check in variations) {
						variationCount += (variations[check] == true) ? 1 : 0;
					}
					score += (variationCount - 1) * 10;

					return parseInt(score);
				},
				
				validate : function(o) {
					o.removeClass(this.field,'warn-box');
					o.removeMessage(this.field);
					
					if ( o.getField('oldPassword').value == "" )
						return true;
					
					var value = o.getField(this.field).value;
					if ( this.score(value) <= 70 ) {
						o.addClass(this.field,'warn-box');
						o.addMsg(this.field,__tr('Weak password'));
						return false;
					}
					return true;
				}
			},
			{
				field : 'userPasswordRepeat',
				
				validate : function(o) {
					o.removeClass(this.field,'warn-box');
					o.removeMessage(this.field);
					
					
					if ( o.getField('userPassword').value !== o.getField(this.field).value ) {
						o.addClass(this.field,'warn-box');
						o.addMsg(this.field,__tr('Passwords do not match'));
						return false;
					}
					return true;
				}
			},
			{
				field : 'firstName',
				validate : function(o) {
					o.removeClass(this.field,'warn-box');
					o.removeMessage(this.field);
					
					if ( o.getField(this.field).value === '' ) {
						o.addClass(this.field,'warn-box');
						o.addMsg(this.field,__tr('Invalid first name'));
						return false;
					}
					return true;
				}
			},
			{
				field : 'lastName',
				validate : function(o) {
					o.removeClass(this.field,'warn-box');
					o.removeMessage(this.field);
					
					if ( o.getField(this.field).value === '' ) {
						o.addClass(this.field,'warn-box');
						o.addMsg(this.field,__tr('Invalid last name'));
						return false;
					}
					return true;
				}
			}
			
			]);
	}
	 
});
