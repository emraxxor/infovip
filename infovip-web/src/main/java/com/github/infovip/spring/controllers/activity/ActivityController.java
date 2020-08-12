package com.github.infovip.spring.controllers.activity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.data.DefaultESSimpleGetElement;
import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.data.Field;
import com.github.infovip.core.data.IndexMetaData;
import com.github.infovip.core.data.UserPublicElement;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.es.query.DocumentManager;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.UserSessionInterface;
import com.github.infovip.entities.User;
import com.github.infovip.spring.services.ActivityService;
import com.github.infovip.web.application.es.activity.ActivityCommentElement;
import com.github.infovip.web.application.es.activity.ActivityElement;
import com.github.infovip.web.application.es.activity.ActivityJoinType;
import com.github.infovip.web.application.es.activity.ActivityLikeDataElement;
import com.github.infovip.web.application.es.activity.ActivityLikeElement;
import com.github.infovip.web.application.es.activity.ActivityPost;
import com.github.infovip.web.application.es.activity.ActivityPostElement;
import com.google.gson.reflect.TypeToken;


/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private DocumentManager documentManager;
	
	private Logger logger = Logger.getLogger(ActivityController.class);
	
    @RequestMapping(path="/data",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object data(
    		@RequestParam(name="token",defaultValue="",required=false) String token,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	ActivitySource source = new ActivitySource(context, token);
    	try {
			return ScrollResponseGenerator.generate( new DefaultScrollResponse<UserPublicElement<?>,WebApplicationContext>(),  source, request, response);
		} catch (UnsupportedTypeException e) {
			logger.error(e.getMessage(),e);
			return null;
		} 
    }
    
    @RequestMapping(path="/nolike", method = RequestMethod.POST)
    public @ResponseBody Object nolike(
    		@RequestParam String id,
    		@RequestParam String routing,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	
    	List<Field> fields = new ArrayList<>();
    	fields.add(new Field("doc.keyword", id));
    	fields.add(new Field("uid", u.getUserId()));
    	
    	ActivityLikeElement ale = documentManager.findDocumentByField(fields, IndexMetaData.create(
    											DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX, 
    											DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE)
    										    .addExclusionStrategy(ActivitySource.DefaultActivityExclusionStrategy()) , 
    								new TypeToken<ActivityLikeElement>(){}.getType()  );
    
    	
    	
    	if ( ale != null && ale.getUid().equals(u.getUserId()) ) {
			return esContainer.executeSynchronusRequest(
					new DefaultDataElement<ActivityLikeElement>(ale)
						.setIndex(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX)
						.setType(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE)
						.setRouting(routing)
						.operationDelete()
			);
    	}
    
    	return null;
    }

    
    @RequestMapping(path="/like", method = RequestMethod.POST)
    public @ResponseBody Object like(
    		@RequestParam String id,
    		@RequestParam String routing,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	
    	ActivityPost<ActivityElement> doc = new ActivityPost<ActivityElement>(new ActivityElement());
    	doc.setDocumentId(id);
    	
    	DefaultESSimpleGetElement<ActivityElement, ActivityPost<ActivityElement>> deg = new DefaultESSimpleGetElement<ActivityElement, ActivityPost<ActivityElement>>( doc , new TypeToken<ActivityElement>(){}.getType());
    	deg.exclusionStrategies().add(ActivitySource.DefaultActivityExclusionStrategy());
    	deg.setRouting(routing);
    	
    	esContainer.search(deg);
    	
    	List<Field> fields = new ArrayList<>();
    	fields.add(new Field("doc.keyword", id));
    	fields.add(new Field("uid", u.getUserId()));
    
    	ActivityLikeElement ale = documentManager.findDocumentByField(fields, IndexMetaData.create(
				DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX , 
				DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE
				).addExclusionStrategy(ActivitySource.DefaultActivityExclusionStrategy()) , 
    				new TypeToken<ActivityElement>(){}.getType()  );
    	
    	if ( ale == null && deg.data() != null ) {
			if ( deg.data().getPostType().equals(ActivityJoinType.POST.value() ) ) {
				deg.elem().convertDataElement(ActivityPostElement.class);
				( (ActivityPostElement) deg.data() ).setJoin( ActivityJoinType.POST.value() );
				// @UPDATE
				ActivityLikeDataElement<ActivityLikeElement> data = new ActivityLikeDataElement<ActivityLikeElement>(new ActivityLikeElement(deg.data().getDocumentId(), u.getUserId(), ActivityJoinType.LIKE_POST ) );
				data.data().setUid(u.getUserId());
				data.setRouting( routing );
				esContainer.executeSynchronusRequest(data.operationIndex());
			} else if (  deg.data().getPostType().equals(ActivityJoinType.COMMENT.value() )  ) {
				deg.elem().convertDataElement(ActivityCommentElement.class);
				( (ActivityCommentElement) deg.data() ).setJoin( new ESDefaultJoinRelation(ActivityJoinType.COMMENT.value(), deg.data().getParentDocument() ) );
				// @UPDATE
				ActivityLikeDataElement<ActivityLikeElement> data = new ActivityLikeDataElement<ActivityLikeElement>(new ActivityLikeElement(deg.data().getDocumentId(), u.getUserId(), ActivityJoinType.LIKE_COMMENT ) );
				data.data().setUid(u.getUserId());
				data.setRouting(routing);
				esContainer.executeSynchronusRequest(data.operationIndex());
			}
    		return deg.data();
    	}
    	
    	return null;
    }

    
    @RequestMapping(path="/reply", method = RequestMethod.POST)
    public @ResponseBody Object comment(
    		@RequestParam String id,
    		@RequestParam String routing,
    		@RequestParam String text,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	
    	ActivityCommentElement ace = new ActivityCommentElement(id);
    	ace.setUid(u.getUserId());
    	ace.setUserName(u.getUserRealName());
    	ace.setText(text);
    	ace.setPostType(ActivityJoinType.REPLY.value());
    	ace.setJoin(new ESDefaultJoinRelation(ActivityJoinType.REPLY.value(), id));
    	ActivityPost<ActivityCommentElement> doc = new ActivityPost<ActivityCommentElement>(ace);
    	doc.setRouting(routing);
    	
    	IndexResponse ir = (IndexResponse) esContainer.executeSynchronusRequest(doc.operationIndex());
    	
    	ace.setDocumentId(ir.getId());
    	ace.setRouting(routing);
    	return ace;
    }
    
    
    @RequestMapping(path="/comment", method = RequestMethod.POST)
    public @ResponseBody Object comment(
    		@RequestParam String id,
    		@RequestParam String text,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	
    	ActivityCommentElement ace = new ActivityCommentElement(id);
    	ace.setUid(u.getUserId());
    	ace.setUserName(u.getUserRealName());
    	ace.setText(text);
    	
    	ActivityPost<ActivityCommentElement> doc = new ActivityPost<ActivityCommentElement>(ace);
    	doc.setRouting(id);
    	
    	IndexResponse ir = (IndexResponse) esContainer.executeSynchronusRequest(doc.operationIndex());
    	ace.setDocumentId(ir.getId());
    	ace.setRouting(id);
    	
    	return ace;
    	
    }

	
    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody Object add(
    		@ModelAttribute ActivityPostElement post,
    		BindingResult result, 
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	post.setUid(u.getUserId());
    	post.setUserName(u.getUserRealName());
    	ActivityPost<ActivityPostElement> doc = new ActivityPost<ActivityPostElement>(post);
    	IndexResponse ir = (IndexResponse) esContainer.executeSynchronusRequest(doc.operationIndex());
    	return documentManager.findByDocumentId(ir.getId(), ir.getIndex(),  new TypeToken<ActivityPostElement>(){}.getType() );
    }
	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.activity.page");
    	return mv;
    }

}
