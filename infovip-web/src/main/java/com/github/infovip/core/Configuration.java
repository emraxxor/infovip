package com.github.infovip.core;

/**
 *
 * @author attila
 */
public class Configuration {

    /**
     * Identifier of the default SQL connection It is used by the basic SQL
     * manager component
     */
    public static final String BEAN_SQL_ID = "DefaultJSQLConnection";

    /**
     * Identifier of the default JSP module manager component
     */
    public static final String BEAN_MODULE_ID = "JSPModuleManager";

    /**
     * Location of the modules directory
     */
    public static final String MODULE_DIRECTORY = "/modules";

    /**
     * Location of the web directory
     */
    public static final String WEB_DIRECTORY = "/WEB-INF/web";

    /**
     * Location of the resources directory
     */
    public static final String RESOURCES_DIRECTORY = "/resources";

    /**
     * Location of the modules directory
     */
    public static final String DEFAULT_MODULE_DIRECTORY = "/WEB-INF/web/modules";

    /**
     * Identifier of the application's context path
     */
    public static final String CONTEXT_PATH_ID = "contextPath";

    /**
     * Identifier of the resources
     */
    public static final String RESOURCES_ID = "resources";

    /**
     * Container identifier
     */
    public static final String CONTAINER_ID = "container";

    /**
     * Title of the page
     */
    public static String PAGE_TITLE = "Infovip";

    /**
     * Application's context name
     */
    public static final String APPLICATION_CONTEXT_NAME = "/infovip-web";
    
    /**
     * If it is set to true then the debug messages are displayed
     */
    public static final Boolean DEBUG = true;

    /**
     * Container id
     */
    public static final String BEAN_CONTAINER_ID = "container";

    public String getBEAN_SQL_ID() {
        return Configuration.BEAN_SQL_ID;
    }

    public String getBEAN_MODULE_ID() {
        return Configuration.BEAN_MODULE_ID;
    }

    public String getMODULE_DIRECTORY() {
        return Configuration.MODULE_DIRECTORY;
    }

    public String getDEFAULT_MODULE_DIRECTORY() {
        return Configuration.DEFAULT_MODULE_DIRECTORY;
    }

    public String getCONTEXT_PATH_ID() {
        return Configuration.CONTEXT_PATH_ID;
    }

    public String getPAGE_TITLE() {
        return Configuration.PAGE_TITLE;
    }

    public void setPAGE_TITLE(String title) {
        Configuration.PAGE_TITLE = title;
    }

    public String getCONTAINER_ID() {
        return Configuration.CONTAINER_ID;
    }

    public String getWEB_DIRECTORY() {
        return Configuration.WEB_DIRECTORY;
    }
}
