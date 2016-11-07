<%-- 
    Document   : header
    Created on : Jul 9, 2016, 5:22:44 PM
    Author     : attila
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute id="currentPageCss" name="pageCss" classname="java.util.List" />
<tilesx:useAttribute id="currentPageJs" name="pageJs" classname="java.util.List" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${Configuration.PAGE_TITLE}</title>
        <c:forEach items="${container.cssManager.data}" var="css">
            ${css}
        </c:forEach>
        
         <c:forEach items="${currentPageCss}" var="css">
			 <link rel='stylesheet' href='${resource}/${css}' type='text/css' media='screen' />
        </c:forEach>
        
        <c:forEach items="${container.javascriptManager.data}" var="js">
            ${js}
        </c:forEach>
        
        <c:forEach items="${currentPageJs}" var="js">
        	 <script type='text/javascript' src='${resources}/${js}'></script>
        </c:forEach>
    </head>
    <body class="claro">
        <div id="main-page">
	        <tiles:insertAttribute name="header"/>
			<tiles:insertAttribute name="navbar"/>
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer"/>
		</div>
	</body>
</html>