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


	/**
	 * 
	 */
	private static final long serialVersionUID = 1308425350042743086L;

	public JavascriptManager() throws UnsupportedTypeException {
        super();
        this.addElement(new JavaScript("https://www.google.com/recaptcha/api.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.WEBPACK_DIRECTORY + "/shared.bundle.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.WEBPACK_DIRECTORY + "/bootstrap.bundle.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.WEBPACK_DIRECTORY + "/vue.bundle.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.WEBPACK_DIRECTORY + "/app.bundle.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/highslide/highslide/highslide-full.packed.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/runnable/Runnable.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/Configuration.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/ScriptLoader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/Translator.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/AjaxManager.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/dependency/DependencyLoader.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/lib/base64.min.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/Core.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/dependency/Module.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/core/dependency/ModuleManager.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));
        this.addElement(new JavaScript(Configuration.APPLICATION_CONTEXT_NAME + Configuration.RESOURCES_DIRECTORY + "/js/DynamicModuleManager.js", JavaScript.ScriptType.TEXT_JAVASCRIPT));

    }

}
