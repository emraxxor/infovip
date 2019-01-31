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
        <script src="https://apis.google.com/js/platform.js" async defer></script>
		
		<script>
		  window.dataLayer = window.dataLayer || [];
		  function gtag(){dataLayer.push(arguments);}
		  gtag('js', new Date());
		</script>
        
        <c:choose>
	    	<c:when test="${not empty metaTitle}">
		  		<title>${metaTitle}</title>
  		 	</c:when>
	  		<c:otherwise>
    		   <title>${container.pageTitle}</title>
	   	   </c:otherwise>
		</c:choose>
		
		
		<meta charset=utf-8 >
		<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
		<meta name="robots" content="index, follow" /> 
		<c:if test="${not empty metaDescription}">
        	<meta name="description" content="${metaDescription}" />
        </c:if>
        <c:if test="${not empty metaKeywords}">
        	<meta name="keywords" content="${metaKeywords}" />
        </c:if>
        
        <c:if test="${not empty ogTitle}">
        	<meta property="og:title" content="${ogTitle}" />
        </c:if>
        
        <c:if test="${not empty ogType}">
        	<meta property="og:type" content="${ogType}" />
        </c:if>
        
        <c:if test="${not empty ogDescription}">
        	<meta property="og:type" content="${ogDescription}" />
        </c:if>
        
        <c:if test="${not empty ogImage}">
        	<meta property="og:image" content="${Configuration.temporaryConfig.websiteAddress}/ProductImage?${ogImage}" />
        </c:if>
        
         
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		
        
        
        <c:forEach items="${container.cssManager.data}" var="css">
            ${css}
        </c:forEach>
                
        <c:forEach items="${container.javascriptManager.data}" var="js">
            ${js}
        </c:forEach>
        
    </head>
    
    <!-- Preloader Start -->
    <div id="preloader">
        <div class="preloader--inner"></div>
    </div>
    <!-- Preloader End -->
    
    
    <body class="claro">
        <div id="main-page">

    	    <!-- Wrapper Start -->
			    <div class="wrapper">
			    
			    <tiles:insertTemplate template="${Configuration.TILES}/views/widgets/navbar.jsp"></tiles:insertTemplate>  
          
          		