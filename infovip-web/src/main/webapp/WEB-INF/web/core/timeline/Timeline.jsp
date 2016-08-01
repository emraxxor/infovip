<%-- 
    Document   : Timeline
    Created on : Jul 31, 2016, 1:48:54 PM
    Author     : attila
--%>

<%@include  file="../../core/config.jsp" %>

<esentity:entity entity="${post}" var="testPost">
    <esentity:foreach current="testPost">
        UserName : <esentity:out field="userName" /><br>
        ID : <esentity:out field="id" /> <br>
        CreationTime: <esentity:out field="creationTime" /><br>
    </esentity:foreach>
</esentity:entity>