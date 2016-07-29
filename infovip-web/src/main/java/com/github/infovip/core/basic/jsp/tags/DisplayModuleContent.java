package com.github.infovip.core.basic.jsp.tags;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.jsp.ModuleManager;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author attila
 */
public class DisplayModuleContent extends SimpleTagSupport {

    private String moduleName;

    private String moduleDescription;

    private ApplicationContext context;

    private ModuleManager manager;

    @Override
    public void doTag() throws JspException, IOException {
        context = RequestContextUtils.findWebApplicationContext((HttpServletRequest) ((PageContext) getJspContext()).getRequest());
        manager = (ModuleManager) getJspContext().getAttribute(Configuration.BEAN_MODULE_ID,PageContext.REQUEST_SCOPE);
        if (moduleName != null) {
            getJspContext().getOut().println(manager.content(moduleName));
        } else {
            getJspContext().getOut().println(manager.content());

        }
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

}
