<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/template/DefaultUserPage.jsp">
	
	<tiles:putAttribute name="MAIN_CONTENT">

			<div id="membersResult" class="waterfall-container row">
										
			</div>		
	</tiles:putAttribute>
	
</tiles:insertTemplate>  			
