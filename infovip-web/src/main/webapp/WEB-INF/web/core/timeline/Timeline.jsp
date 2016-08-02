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
<p>=======================================================</p>

<esentity:create entityClass="com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity" result="createResult" scope="request">
    <esentity:set field="userName" value="MyUser" type="string" />
    <esentity:set field="creationTime" value="2016-08-02 10:00:00" type="date"/>
    <esentity:set field="userId" value="15" type="long" />
    <esentity:set field="message" value="Mymessage\nmessage" type="string" />
</esentity:create>

<esentity:out entity="${createResult}" field="userName" /> 

<p>=======================================================</p>
<esentity:search entityClass="com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity" result="searchResult" scope="request">
    {"query" : { "match_all" : {} } }
</esentity:search>
<esentity:entity entity="${searchResult}" var="testPost">
    <esentity:foreach current="testPost">
        UserName : <esentity:out field="userName" /><br>
        ID : <esentity:out field="id" /> <br>
        CreationTime: <esentity:out field="creationTime" /><br>
    </esentity:foreach>
</esentity:entity>
