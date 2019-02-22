<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">
	
<div  data-type="cover-image" class="cover--header pt--80 text-center bg--img" data-overlay="0.6" data-overlay-color="white" data-rjs="2" style="background-image: url(/user/image/cover?default);">
            <div class="container">
                <div class="cover--avatar online" data-overlay="0.3" data-overlay-color="primary">
                    <img src="/user/image/cover?default" alt="">
                </div>

                <div class="cover--user-name">
                    <h2 class="h3 fw--600">${user.firstName} ${user.lastName}</h2>
                </div>

                <div class="cover--user-activity">
                    <p><i class="fa mr--8 fa-clock-o"></i>#todo</p>
                </div>

                <div data-type="introduction" class="cover--user-desc fw--400 fs--18 fstyle--i text-darkest">
                    <p>#introduction</p>
                </div>
            </div>
</div>


</c:if>