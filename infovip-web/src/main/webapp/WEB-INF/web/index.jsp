
<%@include  file="core/config.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- Other resources --%>
<%@taglib uri="/tlds/sql.tld" prefix="tsql" %>
<tsql:init dburl="jdbc:mysql://localhost:3306/infovip-github" user="root" password="password" />


<%-- Dependencies --%>

<module:js src="${resources}/js/ui/UILogger.js" />
<module:js src="${resources}/js/ui/UICore.js" />
<module:css src="${resources}/css/main.css" />

<module:InitModule moduleLocation="${Configuration.WEB_DIRECTORY}/authentication/DefaultLoginPage.jsp" moduleQueryString="" />
<module:InitModule moduleLocation="${Configuration.WEB_DIRECTORY}/authentication/TemporaryWelcome.jsp" moduleQueryString="" />
<module:IncludePage type="template" file="header" />
<module:DisplayModuleContent moduleName="DefaultLoginPage" />
<module:DisplayModuleContent moduleName="TempWelcome" />
<module:IncludePage type="template" file="footer" />
<tsql:DestroyConnection />