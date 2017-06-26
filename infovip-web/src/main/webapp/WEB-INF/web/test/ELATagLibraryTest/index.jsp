<%@page import="com.github.infovip.core.Configuration"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.data.elasticsearch.core.ElasticsearchTemplate"%>
<%@page import="com.github.infovip.spring.elasticsearch.entities.TimelineCommentEntity"%>
<%@page import="org.springframework.data.elasticsearch.core.query.IndexQuery"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.github.infovip.spring.services.TimelineService"%>
<%@include file="/WEB-INF/web/core/config.jsp" %>


<%
IndexQuery q = new IndexQuery();
ApplicationContext appContext = (ApplicationContext) request.getAttribute("moduleAppContext");
TimelineService tservice = appContext.getBean(TimelineService.class) ;
ElasticsearchTemplate eTemplate =  (ElasticsearchTemplate) appContext.getBean(Configuration.ELASTICSEARCH_TEMPLATE_NAME) ;
TimelinePostEntity post = new TimelinePostEntity(new DateTime().toDate(),30L,"UserName","Message......","simple");
tservice.save(post);
TimelineCommentEntity comment = new TimelineCommentEntity(post.getId(),new DateTime().toDate(),30L,"user","comment","simple");
q.setParentId(post.getId());
q.setObject(comment);
eTemplate.index(q);
request.setAttribute("post", tservice.findAll());
%>

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
