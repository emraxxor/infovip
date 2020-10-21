<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute id="MENU_BEFORE" name="MENU_BEFORE" classname="java.util.List" />
<tilesx:useAttribute id="MENU_AFTER" name="MENU_AFTER" classname="java.util.List" />



						  <!-- Content Nav Start -->
                            <div class="content--nav pb--30">
                                <ul class="nav ff--primary fs--14 fw--500 bg-lighter">
                                 
	                                <c:forEach items="${MENU_BEFORE}" var="umenu">
										<c:out value="${umenu}" escapeXml="false"></c:out>
									</c:forEach>
									
                                    <li><a href="/user/${CurrentUser.user.userId}/activity">Activity</a></li>
                                    <li><a href="/user/${CurrentUser.user.userId}/profile">Profile</a></li>
                                    <li><a href="/user/${CurrentUser.user.userId}/friends">Friends</a></li>
                                    <li><a href="/user/${CurrentUser.user.userId}/media">Media</a></li>
                                    <li><a href="/user/${CurrentUser.user.userId}/photo">Photo</a></li>
                                     
	                                <c:forEach items="${MENU_AFTER}" var="umenu">
									        <c:out value="${umenu}" escapeXml="false"></c:out><br/>
									</c:forEach>

                                </ul>
                            </div>
                            <!-- Content Nav End -->