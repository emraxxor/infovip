/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.js.JavaScript;

/**
 *
 * @author attila
 */
public class JavascriptManager extends DefaultManager<JavaScript> {

    public JavascriptManager() throws UnsupportedTypeException {
        super();
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/easejs/ease-latest.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/jquery/jquery-3.0.0.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/jquery-ui/jquery-ui.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/webix/codebase/webix.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/jquery/infinite-scroll/jquery.infinitescroll.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/runnable/Runnable.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/Configuration.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/ScriptLoader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/Translator.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/AjaxManager.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/dojo/DojoLoader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/dojo/DojoScriptLoader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/dojo/DojoComponent.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/dojo/DojoComponentExecutor.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/dojo/DojoComponentExecutor.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/bootstrap/bootstrap.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/media/js/jquery.dataTables.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/ColReorder/js/dataTables.colReorder.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/dataTables.buttons.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/buttons.html5.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/buttons.flash.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/buttons.print.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/buttons.colVis.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/js/buttons.jqueryui.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Responsive/js/dataTables.responsive.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Responsive/js/responsive.jqueryui.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/jszip.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/pdfmake.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/vfs_fonts.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
		this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/highslide/highslide/highslide-full.packed.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/Core.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
    }

}
