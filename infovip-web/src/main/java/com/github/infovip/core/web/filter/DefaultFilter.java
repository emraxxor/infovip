package com.github.infovip.core.web.filter;

import static com.github.infovip.core.Configuration.BEAN_MODULE_ID;
import static com.github.infovip.core.Configuration.CONTEXT_PATH_ID;
import static com.github.infovip.core.Configuration.RESOURCES_ID;
import static com.github.infovip.core.Configuration.RESOURCES_DIRECTORY;
import static com.github.infovip.core.session.DefaultUserSession.IS_AUTHENTICATED;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.github.infovip.configuration.DefaultWebAppConfiguration.BaseSessionInformation;
import com.github.infovip.core.basic.jsp.ModuleManager;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.UserSession;

/**
 *
 * @author attila
 */
public class DefaultFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     * Current http session
     */
    private HttpSession session;

    /**
     * Current application scope
     */
    private ServletContext servletContext;

    /**
     * Current request scope
     */
    private HttpServletRequest request;

    public DefaultFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException, UnsupportedTypeException {
        this.request = (HttpServletRequest) request;
        this.servletContext = this.request.getSession().getServletContext();
        this.session = this.request.getSession();

        this.servletContext.setAttribute(CONTEXT_PATH_ID, this.servletContext.getContextPath());
        this.servletContext.setAttribute(RESOURCES_ID, this.servletContext.getContextPath() + RESOURCES_DIRECTORY);
        this.request.setAttribute(BEAN_MODULE_ID, new ModuleManager());
        
        if ( this.session.getAttribute(BaseSessionInformation.USER_SESSION.toString()) != null &&   ( (UserSession) this.session.getAttribute(BaseSessionInformation.USER_SESSION.toString()) ).isAuthenticated() ) 
        	this.request.setAttribute(IS_AUTHENTICATED.value(), true);
        
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {

            doBeforeProcessing(request, response);

            Throwable problem = null;
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException t) {
                problem = t;
            }

            doAfterProcessing(request, response);

            // If there was a problem, we want to rethrow it if it is
            // a known type, otherwise log it.
            if (problem != null) {
                if (problem instanceof ServletException) {
                    throw (ServletException) problem;
                }
                if (problem instanceof IOException) {
                    throw (IOException) problem;
                }
                sendProcessingError(problem, response);
            }
        } catch (UnsupportedTypeException ex) {
            Logger.getLogger(DefaultFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
