<%-- 
    Document   : header
    Created on : Jul 9, 2016, 5:22:44 PM
    Author     : attila
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tr" uri="/tlds/translate" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${Configuration.PAGE_TITLE}</title>
        <c:forEach items="${container.cssManager.data}" var="css">
            ${css}
        </c:forEach>
                
        <c:forEach items="${container.javascriptManager.data}" var="js">
            ${js}
        </c:forEach>
        
    </head>
    <body class="claro">
        <div id="main-page">

          