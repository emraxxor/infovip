<%-- 
    Document   : navbar
    Created on : Jul 31, 2016, 10:00:15 AM
    Author     : attila
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userSession.authenticated}">
    <div class="navbar navbar-subnav navbar-static-top">
        <div class="container">
            <div aria-expanded="false" class="collapse navbar-collapse" id="subnav">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="<c:url value="home"/>">Home</a>
                    </li>
                    <li>
                        <a href="<c:url value="profile"/>">Profile</a>
                    </li>
                    <li>
                        <a href="<c:url value="firends"/>">Friends</a>
                    </li>
                    <li>
                        <a href="<c:url value="messages"/>">Messages</a>
                    </li>
                    <li>
                        <a href="<c:url value="messages"/>">Forum</a>
                    </li>
                    <li>
                        <a href="<c:url value="chat"/>">Chat</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:url value="logout"/>">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</c:if>



