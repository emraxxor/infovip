<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

	<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/template/DefaultUserPage.jsp">
		<tiles:putAttribute name="MAIN_CONTENT">

			<div>
				<div class="main--content-inner drop--shadow">


					<!-- Content Nav Start -->
					<tiles:insertTemplate
						template="${Configuration.WEB_DIRECTORY}/user/header/UserHeaderPage.jsp">
						<tiles:putListAttribute name="MENU_BEFORE"></tiles:putListAttribute>
						<tiles:putListAttribute name="MENU_AFTER"></tiles:putListAttribute>
					</tiles:insertTemplate>
					<!-- Content Nav End -->



					<!-- Filter Nav Start -->
					<div class="filter--nav pb--60 clearfix">
						<div class="filter--links float--left">
							<ul class="nav ff--primary">
								<li class="active"><a href="#">Media filter</a></li>
							</ul>
						</div>

				
					</div>
					<!-- Filter Nav End -->

					<div id="mediawaterfall" class="waterfall-container row">

					</div>

				</div>
			</div>
		</tiles:putAttribute>

	</tiles:insertTemplate>
	
