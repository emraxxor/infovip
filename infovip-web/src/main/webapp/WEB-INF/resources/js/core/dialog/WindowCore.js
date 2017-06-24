jQuery(document).ready(function() {
    DependencyLoader
        .create()
        .js('js/core/dialog/interface/IDialogWindow')
        .js('js/core/dialog/DialogWindow')
        .js('js/core/dialog/DefaultDialogWindow')
        .js('js/core/dialog/modal/ModalComponent')
        .js('js/core/dialog/modal/DefaultModalComponent')
        .js('js/core/dialog/ModalDialogWindow')
        .js('js/core/dialog/ModalDialogWindowDisplayer')
        .css('js/core/dialog/ModalDialogWindow')
        .import();
}); 