<%@include file="/WEB-INF/web/core/config.jsp" %>
<module:InitModule moduleLocation="${modulePath}/TestModule.jsp" moduleQueryString="menu=test" />
<module:InitModule moduleLocation="${modulePath}/TestModule1.jsp" moduleQueryString="" />

<module:js src="${moduleResources}/js/test1.js"/>
<module:IncludePage type="template" file="header" />
<module:DisplayModuleContent moduleName="TestModule1" />
<module:DisplayModuleContent moduleName="TestModule" />
<module:IncludePage type="template" file="footer" />
