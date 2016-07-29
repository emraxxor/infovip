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

    public Container(JavascriptManager jsManager, CSSManager cssManager) throws UnsupportedTypeException {
        this.javascriptManager = jsManager;
        this.cssManager = cssManager;
    }

    public JavascriptManager getJavascriptManager() {
        return javascriptManager;
    }

    public CSSManager getCssManager() {
        return cssManager;
    }
}
