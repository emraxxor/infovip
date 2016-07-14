<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/web/core/config.jsp" %>


<%-- Other resources --%>
<%@taglib uri="/tlds/sql.tld" prefix="tsql" %>
<tsql:init dburl="jdbc:mysql://localhost:3306/infovip-github" user="root" password="password" />


<%-- Dependencies --%>
<module:InitModule moduleLocation="${Configuration.DEFAULT_MODULE_DIRECTORY}/MyModule/TestModule.jsp" moduleQueryString="menu=test" />

<module:IncludePage type="template" file="header" />

Welcome page

<form name="DefaultAuthenticationForm" method="POST" action="${contextPath}/login">
    Name : <input type="text" name="userName" value="" /> <br/>
    Password : <input type="text" name="userPassword" value="" /><br/>
    <input type="submit" value="Sign in" />
</form>

No query string <br/>

Output of the testmodule will be displayed only if the rules are met 
<module:DisplayModuleContent moduleName="TestModule" />




<tsql:SelectQuery var="testSql">SELECT * FROM users</tsql:SelectQuery>
<tsql:Next var="testSql">
    <tsql:Out field="uname" /> 
</tsql:Next>

<tsql:freeQuery var="testSql"></tsql:freeQuery>



<tsql:preparedSelect var="testSql">
    SELECT * FROM users where uid=? LIMIT 1
    <tsql:preparedValue type="int" value="1" />
</tsql:preparedSelect>
<tsql:Next var="testSql">
    <tsql:Out field="uname" /> 
</tsql:Next>     

<module:DisplayModuleContent moduleName="TestModule" />

<module:IncludePage type="template" file="footer" />

<tsql:DestroyConnection />