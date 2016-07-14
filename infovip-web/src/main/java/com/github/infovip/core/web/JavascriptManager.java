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
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/webix/codebase/webix.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
    }
    
}
