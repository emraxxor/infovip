<%-- 
  Default Modules for the application are specified here
  
  Configuration directives can be used as the following : 
  ${Configuration.BEAN_MODULE_ID}

--%>

 <%--
  Module taglib
--%>
<%@taglib uri="/tlds/module-manager" prefix="module" %>

<%-- 
  Translate taglib
--%>
<%@taglib uri="/tlds/translate" prefix="tr" %>


<%-- 
  Web standard tag library
--%>
<%@taglib prefix="web" uri="/tlds/web" %>

<%-- 
  Core standard tag library
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%--
 ES Entity manager
--%>
<%@taglib prefix="esentity" uri="/tlds/entity" %>