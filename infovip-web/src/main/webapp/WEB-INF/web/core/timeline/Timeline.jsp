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
    <esentity:set field="creationTime" value="2016-08-02 19:36:46" type="date"/>
    <esentity:set field="userId" value="15" type="long" />
    <esentity:set field="message" value="Mymessage\nmessage" type="string" />
</esentity:create>

<esentity:out entity="${createResult}" field="userName" /> 

<esentity:set entity="${createResult}" field="userName" type="string" value="modifiedValue" />
<esentity:out entity="${createResult}" field="userName" />
<esentity:update entity="${createResult}" />

<p>=======================================================</p>
<esentity:search entityClass="com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity" result="searchResult" scope="request">
    {"query" : { "query_string" : { "default_field" : "userName","query": "modifiedValue" } } }
</esentity:search>
<esentity:entity entity="${searchResult}" var="testPost">
    <esentity:foreach current="testPost">
        UserName : <esentity:out field="userName" /><br>
        ID : <esentity:out field="id" /> <br>
        CreationTime: <esentity:out field="creationTime" /><br>
    </esentity:foreach>
</esentity:entity>

<esentity:delete entity="${createResult}"/>
