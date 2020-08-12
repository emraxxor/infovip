<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">

	<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/template/DefaultUserPage.jsp">
		<tiles:putAttribute name="MAIN_CONTENT">

			<div class="theiaStickySidebar"
				style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none;">
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
								<li class="active"><a href="#personal">Personal</a></li>
								<li><a href="#mentions">Mentions</a></li>
								<li><a href="#favorites">Favorites</a></li>
								<li><a href="#friends">Friends</a></li>
								<li><a href="#groups">Groups</a></li>
							</ul>
						</div>

						<div class="filter--options float--right">
							<label> <span
								class="fs--14 ff--primary fw--500 text-darker">Show By :</span>

								<select name="activityfilter" class="form-control form-sm"
								data-trigger="selectmenu" style="display: none;">
									<option value="updates" selected="">Updates</option>
									<option value="friendships">Friendships</option>
									<option value="group-updates">Group Updats</option>
									<option value="membership">Membership</option>
									<option value="topics">Topics</option>
									<option value="replies">Replies</option>
									<option value="posts">Posts</option>
									<option value="comments">Comments</option>
							</select>
							<div class="custom-select-menu" tabindex="0">
									<span class="selection-made">Updates</span>
									<ul data-select-name="activityfilter" style="display: none;">
										<li data-option-value="updates" class="selected">Updates</li>
										<li data-option-value="friendships">Friendships</li>
										<li data-option-value="group-updates">Group Updats</li>
										<li data-option-value="membership">Membership</li>
										<li data-option-value="topics">Topics</li>
										<li data-option-value="replies">Replies</li>
										<li data-option-value="posts">Posts</li>
										<li data-option-value="comments">Comments</li>
									</ul>
									<input type="hidden" name="activityfilter" value="updates">
								</div>
							</label>
						</div>
					</div>
					<!-- Filter Nav End -->

					<!-- Activity List Start -->
					<div class="activity--list">
						<!-- Activity Items Start -->
						<ul class="activity--items">


						</ul>
						<!-- Activity Items End -->
					</div>
					<!-- Activity List End -->


				</div>
				<div class="load-more--btn pt--30 text-center">
					<a href="#" class="btn btn-animate"> <span>See More
							Activities<i class="fa ml--10 fa-caret-right"></i>
					</span>
					</a>
				</div>

			</div>

		</tiles:putAttribute>

	</tiles:insertTemplate>
	
</c:if>