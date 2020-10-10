<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">

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

						<c:if test="${ userSession.user.userId == MediaUserId }">
							<div class="filter--options float--right">
									<div class="gallery--upload-btn ff--primary float--right">
	                                    <a class="btn btn-sm btn-default btn-new-album"><i class="mr--10 fa fa-cloud-upload"></i>New Album</a>
	                                </div>
							</div>
						</c:if>
					</div>
					<!-- Filter Nav End -->

					<div id="mediawaterfall" class="waterfall-container row">

					</div>

				</div>
			</div>
		</tiles:putAttribute>

	</tiles:insertTemplate>
	
</c:if>		
