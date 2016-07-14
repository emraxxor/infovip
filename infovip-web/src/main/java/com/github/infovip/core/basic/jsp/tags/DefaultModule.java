package com.github.infovip.core.basic.jsp.tags;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.jsp.ModuleManager;
import com.github.infovip.core.basic.jsp.exceptions.ModuleAlreadyExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author attila
 */
public class DefaultModule extends BodyTagSupport {

    private ApplicationContext context;

    private ModuleManager manager;

    private String moduleName;

    private String moduleDescription;

    /**
     * Creates new instance of tag handler
     */
    public DefaultModule() {
        super();

    }

    @Override
    public int doStartTag() throws JspException {
        context = RequestContextUtils.findWebApplicationContext((HttpServletRequest) pageContext.getRequest());
        manager = (ModuleManager) pageContext.getAttribute(Configuration.BEAN_MODULE_ID, PageContext.REQUEST_SCOPE);
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (!manager.existsModule(moduleName)) {
            manager.addModule(this);
        } else {
            throw new ModuleAlreadyExistsException(String.format("Module : %s already exists!", moduleName));
        }
        return SKIP_BODY;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public ModuleManager getManager() {
        return manager;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

}
