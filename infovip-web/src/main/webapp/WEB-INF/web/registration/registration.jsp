<%@include file="/WEB-INF/web/core/config.jsp" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<module:IncludePage type="template" file="header" />
<h1>Default Registration Site</h1>
<form:form method="post" modelAttribute="user" acceptCharset="UTF-8" autocomplete="off" action="registration/add">
    <tr:tr val="UserName" /> : <form:input path="uname"></form:input>
    <tr:tr val="Password" /> : <form:input path="upassword"></form:input>
    <tr:tr val="Email" /> : <form:input path="umail"></form:input>
        <input type="submit" value="Submit" />
</form:form>
Val ${k1}

<module:IncludePage type="template" file="footer" />

