
<%@include file="/WEB-INF/web/core/config.jsp" %>

<module:InitModule moduleLocation="${modulePath}/TestModule.jsp" moduleQueryString="menu=test" />


<%
    out.println(((PageContext) pageContext).getServletContext().getContextPath());
%>    

${Configuration.MODULE_DIRECTORY}/MyModule/TestModule.jsp

${Configuration.DEFAULT_MODULE_DIRECTORY}/MyModule/TestModule.jsp

${modulePath}




<script type="text/javascript" src="${moduleResources}/js/test1.js"></script>

<module:IncludePage type="template" file="header" />

No query string <br/>


<module:DisplayModuleContent moduleName="TestModule" />

<module:IncludePage type="template" file="footer" />
