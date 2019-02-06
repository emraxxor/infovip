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
        .js('js/ui/tinymce/TinyMceManager')
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
        .js('js/ui/iscroll/IScroll')
        .js('js/ui/tinymce/TinyMceEditor')
        .js('js/core/form/DefaultHTMLForm')
        .js('js/core/form/DefaultHTMLFormValidator')
        .js('js/web/user/settings/general/GeneralSettingsValidator')
        .js('js/web/user/settings/general/GeneralSettingsForm')
        .js('js/web/user/settings/general/GeneralSettingsController')
        .import(function(args){
        	new UIControllerExecutor(  new GeneralSettingsController(jQuery('div.main--content')) ).execute(); 
       },{});
}); 