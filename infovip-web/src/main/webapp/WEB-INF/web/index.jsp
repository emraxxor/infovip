
<%@include  file="core/config.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>



<module:InitModule moduleLocation="${Configuration.WEB_DIRECTORY}/authentication/DefaultLoginPage.jsp" moduleQueryString="" />
<module:InitModule moduleLocation="${Configuration.WEB_DIRECTORY}/authentication/TemporaryWelcome.jsp" moduleQueryString="" />

<module:DisplayModuleContent moduleName="DefaultLoginPage" />
<module:DisplayModuleContent moduleName="TempWelcome" />


