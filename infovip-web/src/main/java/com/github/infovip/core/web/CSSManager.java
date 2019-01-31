/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.js.CSS;

/**
 *
 * @author attila
 */
public class CSSManager extends DefaultManager<CSS> {

    public CSSManager() throws UnsupportedTypeException {
        super();
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/css/main.css", CSS.CSSType.TEXT_CSS, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/css/navbar.css", CSS.CSSType.TEXT_CSS, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.LIB_DIRECTORY + "/jquery-ui/1.12.1/jquery-ui.min.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/webix/codebase/webix.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/bootstrap/css/bootstrap.min.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/bootstrap/css/bootstrap-theme.min.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/highslide/highslide/highslide.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/media/css/jquery.dataTables.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/media/css/jquery.dataTables_themeroller.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/css/buttons.dataTables.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/Buttons/css/buttons.jqueryui.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/FixedHeader/css/fixedHeader.dataTables.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/FixedHeader/css/fixedHeader.jqueryui.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/ColReorder/css/colReorder.dataTables.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/datatables/extensions/ColReorder/css/colReorder.jqueryui.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
		this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/css/core.css", CSS.CSSType.TEXT_CSS, CSS.CSSREL.STYLESHEET));

    }

}
