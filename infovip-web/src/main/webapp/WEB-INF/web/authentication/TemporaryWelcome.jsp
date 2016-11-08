<%-- 
    Document   : TemporaryWelcome
    Created on : Jul 29, 2016, 6:59:43 PM
    Author     : attila

Only for testing authentication...
--%>

<%@taglib uri="/tlds/module-manager" prefix="module"%>

<%-- 
  Core standard tag library
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${userSession.authenticated}">
	<module:title title="Welcome" />
	 Welcome : ${userSession.userName}
    <p />
	<a href="logout">Logout</a>
</c:if>



