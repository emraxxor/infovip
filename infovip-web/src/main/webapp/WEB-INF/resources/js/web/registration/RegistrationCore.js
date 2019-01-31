/**
 *  @author Attila Barna
 */
jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/dialog/interface/IDialogWindow')
        .js('js/core/dialog/DialogWindow')
        .js('js/core/dialog/DefaultDialogWindow')
        .js('js/core/dialog/modal/ModalComponent')
        .js('js/core/dialog/modal/DefaultModalComponent')
        .js('js/core/dialog/ModalDialogWindow')
        .css('js/core/dialog/ModalDialogWindow')
        .js('js/core/dialog/ModalDialogWindowDisplayer')
        .js('js/core/window/BaseWindow')
        .js('js/core/window/BaseDialogWindow')
        .js('js/core/window/WindowDisplayer')
        .js('js/core/ui/DefaultErrorHandler')
        .js('js/core/ui/DefaultStatusHandler')
        .js('js/core/ui/StatusDisplayer')
        .js('js/core/ui/DefaultConfirmationDialog')
        .js('js/core/ui/DefaultAlertDialog')
        .js('js/core/ui/DefaultInformationDialog')
        .js('js/core/ui/DefaultWarningDialog')
        .js('js/core/ui/DefaultFormValidatorHandlerDialog')
        .js('js/core/UIController')
        .js('js/core/UIControllerExecutor')
        .js('js/core/form/DefaultHTMLForm')
        .js('js/core/form/DefaultHTMLFormValidator')
        .js('js/web/registration/RegistrationForm')
        .js('js/web/registration/RegistrationValidator')
        .js('js/web/registration/RegistrationController')
        .import(function(args){
        	new UIControllerExecutor(  new RegistrationController() ).execute(); 
       },{});
}); 