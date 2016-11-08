<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute id="currentPageCss" name="pageCss" classname="java.util.List" />
<tilesx:useAttribute id="currentPageJs" name="pageJs" classname="java.util.List" />

 <c:forEach items="${currentPageCss}" var="css">
 		<module:css src="${resources}/${css}" />
 </c:forEach>
 
<c:forEach items="${currentPageJs}" var="js">
        <module:js src="${resources}/${js}" />
</c:forEach>
      		 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="${Configuration.WEB_DIRECTORY}/template/header.jsp" />
	        <tiles:insertAttribute name="header"/>
			<tiles:insertAttribute name="navbar"/>
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer"/>
<jsp:include page="${Configuration.WEB_DIRECTORY}/template/footer.jsp" />