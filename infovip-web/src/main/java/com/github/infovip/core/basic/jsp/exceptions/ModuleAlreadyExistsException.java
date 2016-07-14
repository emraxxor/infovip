package com.github.infovip.core.basic.jsp.exceptions;

import javax.servlet.jsp.JspException;

/**
 *
 * @author attila
 */
public class ModuleAlreadyExistsException extends JspException {

    public ModuleAlreadyExistsException(String msg) {
        super(String.format("%s . ModuleName must be unique at each servlet!!", msg));
    }
}
