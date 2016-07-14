package com.github.infovip.core;

import com.github.infovip.core.web.CSSManager;
import com.github.infovip.core.web.JavascriptManager;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;

/**
 *
 * @author attila
 */
public class Container {

    private final JavascriptManager javascriptManager;

    private final CSSManager cssManager;

    public Container() throws UnsupportedTypeException {
        this.javascriptManager = new JavascriptManager();
        this.cssManager = new CSSManager();
    }

    public JavascriptManager getJavascriptManager() {
        return javascriptManager;
    }

    public CSSManager getCssManager() {
        return cssManager;
    }
}
