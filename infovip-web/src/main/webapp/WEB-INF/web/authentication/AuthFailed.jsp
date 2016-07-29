<%@include  file="../core/config.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%-- Dependencies --%>

<module:js src="${resources}/js/ui/UILogger.js" />
<module:js src="${resources}/js/ui/UICore.js" />
<module:css src="${resources}/css/main.css" />

<module:title title="Authentication Failer Error" />

<module:InitModule moduleLocation="${Configuration.WEB_DIRECTORY}/authentication/DefaultLoginPage.jsp" moduleQueryString="" />
<module:IncludePage type="template" file="header" />
<center>The user name or password is incorrect.</center>
<module:IncludePage type="template" file="footer" />