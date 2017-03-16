package com.github.infovip.core.basic.jsp.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.jsp.ModuleManager;

/**
 *
 * @author attila
 */
public class DefaultModule extends BodyTagSupport {

    protected ApplicationContext context;

    private ModuleManager manager;

    private String moduleName;

    private String moduleDescription;

    /**
     * The BodyContent will be cleared on JBOSS, so this is the reason why it is needed to store the bodycontent value in this variable
     */
    protected String moduleContent;
    
    /**
     * Default interceptor for the module
     */
    private String interceptor;

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
        if (manager != null) {
            if (!manager.existsModule(moduleName)) {
                manager.addModule(this);
            }
        }
        this.moduleContent = this.getBodyContent().getString();
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

    public String getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(String interceptor) {
        this.interceptor = interceptor;
    }
    
    public String getModuleContent() {
		return moduleContent;
	}

    /**
     * Gets the current application context
     * @return 
     */
    public ApplicationContext getApplicationContext() {
        return this.context;
    }

    /**
     * Gets the current pagecontext
     * @return 
     */
    public PageContext getPageContext() {
        return this.pageContext;
    }

}
