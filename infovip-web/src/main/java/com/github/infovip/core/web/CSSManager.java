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
        this.addElement(new CSS(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/highslide/highslide/highslide.css", CSS.CSSType.TEXT_CSS, CSS.CSSMedia.SCREEN, CSS.CSSREL.STYLESHEET));
    }

}
