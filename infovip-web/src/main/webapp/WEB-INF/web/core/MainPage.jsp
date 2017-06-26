<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>

<c:if test="${userSession.authenticated}">
	<module:title title="Welcome" />
</c:if>

<module:WebModule moduleName="MainPage" authenticated="true"> 
	<tiles:insertTemplate template="${Configuration.TILES}/views/widgets/navbar.jsp"></tiles:insertTemplate>
	
</module:WebModule>