<%-- 
    Document   : TemporaryWelcome
    Created on : Jul 29, 2016, 6:59:43 PM
    Author     : attila

Only for testing authentication...
--%>

<%@taglib uri="/tlds/module-manager.tld" prefix="module" %>

<%-- 
  Core standard tag library
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<module:title title="Welcome" />


<module:WebModule moduleName="TempWelcome" authenticated="true"> 
    Welcome : ${userSession.userName}
    <p/>
    <a href="logout">Logout</a>
</module:WebModule>

