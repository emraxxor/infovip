<%@include file="/WEB-INF/web/core/config.jsp" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="module" uri="/tlds/module-manager.tld" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<module:js src="${resources}/js/web/Registration.js"/>
<module:title title="Registration" />

<module:IncludePage type="template" file="header" />
<c:if test="${!userSession.authenticated}">
    <div id="main-page-central-panel">
        <div id="default-registration-form"></div>
    </div>
</c:if>
<module:IncludePage type="template" file="footer" />

