<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">

	<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/template/DefaultUserPage.jsp">
		<tiles:putAttribute name="MAIN_CONTENT">

			<div class="theiaStickySidebar"
				style="padding-top: 0px; padding-bottom: 1px; position: relative; transform: none;">
				<div class="main--content-inner drop--shadow">


					<!-- Content Nav Start -->
					<tiles:insertTemplate
						template="${Configuration.WEB_DIRECTORY}/user/header/UserHeaderPage.jsp">
						<tiles:putListAttribute name="MENU_BEFORE"></tiles:putListAttribute>
						<tiles:putListAttribute name="MENU_AFTER"></tiles:putListAttribute>
					</tiles:insertTemplate>
					<!-- Content Nav End -->


					<!-- Profile Details Start -->
					<div class="profile--details fs--14">
						<!-- Profile Item Start -->
						<div class="profile--item">
							<div class="profile--heading">
								<h3 class="h4 fw--700">
									<span class="mr--4">About Me</span> <i
										class="ml--10 text-primary fa fa-caret-right"></i>
								</h3>
							</div>

							<div class="profile--info">
								<table class="table">
									<tbody>
										<tr>
											<th class="fw--700 text-darkest">Full Name</th>
											<td><a href="#" class="btn-link">${user.firstName}
													${user.lastName}</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- Profile Item End -->

						<!-- Profile Item Start -->
						<div class="profile--item">
							<div class="profile--heading">
								<h3 class="h4 fw--700">
									<span class="mr--4">Biography</span> <i
										class="ml--10 text-primary fa fa-caret-right"></i>
								</h3>
							</div>

							<div class="profile--info">
								<p>
									Text
								</p>
							</div>
						</div>
						<!-- Profile Item End -->


						<!-- Profile Item Start -->
						<div class="profile--item">
							<div class="profile--heading">
								<h3 class="h4 fw--700">
									<span class="mr--4">Contact</span> <i
										class="ml--10 text-primary fa fa-caret-right"></i>
								</h3>
							</div>

							<div class="profile--info">
								<table class="table">
									<tbody>
										<tr>
											<th class="fw--700 text-darkest">Phone</th>
											<td><a href="tel:+16105598246">+1610-559-8246</a></td>
										</tr>
										<tr>
											<th class="fw--700 text-darkest">E-mail</th>
											<td><a href="mailto:demo@fakemail.com">demo@example.com</a></td>
										</tr>
										<tr>
											<th class="fw--700 text-darkest">Website</th>
											<td><a href="#">example.com</a></td>
										</tr>
										<tr>
											<th class="fw--700 text-darkest">Address</th>
											<td>123 Lorem St., California, United States</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<!-- Profile Item End -->
					</div>
					<!-- Profile Details End -->
				</div>
			</div>

		</tiles:putAttribute>

	</tiles:insertTemplate>
</c:if>