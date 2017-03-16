<%-- 
    Document   : DefaultLoginPage
    Created on : Jul 19, 2016, 3:25:15 PM
    Author     : attila
--%>
<%@taglib uri="/tlds/module-manager" prefix="module" %>

<%-- 
  Core standard tag library
--%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<module:title title="Login page" />


<module:DefaultModule moduleName="DefaultLoginPage">
    <c:if test="${!userSession.authenticated}">
        <div id="main-page-login-form">
            <div id="default-login-form"></div>
        </div>
    </c:if>
</module:DefaultModule>

