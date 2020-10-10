package com.github.infovip.spring.controllers.user;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.validator.DefaultFormValidator;
import com.github.infovip.core.web.response.StatusResponse;
import com.github.infovip.core.web.types.ImageData;
import com.github.infovip.core.web.user.UserSessionInterface;
import com.github.infovip.core.web.user.media.UserMediaElement;
import com.github.infovip.core.web.user.media.UserPhotoCommentElement;
import com.github.infovip.core.web.user.media.UserPhotoElement;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {

	@Autowired
	private UserServiceInterface<User> userService;

	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private UserSessionInterface<User> userSession;
	
	private User user;
	
	private Long userId;
	
	@PostConstruct
	public void init() {
	   this.user = userSession.getUser();
	   this.userId = userSession.userId();
	}


	@RequestMapping(path = { "/photo/comment" }, method = { RequestMethod.POST })
	public @ResponseBody Object comment(@ModelAttribute UserPhotoCommentElement comment, HttpServletRequest request,
			HttpServletResponse response, SessionStatus status, Model model) {

		comment.setCommenter( userId );
		comment.setCommenterName(user.getUserName());

		DefaultFormValidator<UserPhotoCommentElement> validator = new DefaultFormValidator<>(comment);

		if (validator.validate()) {
			IndexResponse ir = (IndexResponse) esContainer
					.executeSynchronusRequest(new DefaultDataElement<UserPhotoCommentElement>(comment)
							.setIndex(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO_COMMENT));

			comment.setDocumentId(ir.getId());
			return comment;
		}

		return validator.responses();
	}

	@RequestMapping(value = { "/media/upload" }, method = { RequestMethod.POST })
	public @ResponseBody Object upload(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "src", required = true) String src,
			@RequestParam(name = "name", required = true) String name, HttpServletRequest request,
			HttpServletResponse response, SessionStatus status, Model model) throws IOException {

		File f = ImageData.randomFileName(DefaultWebAppConfiguration.MEDIA_IMAGE_PATH);
		File flarge = new File(DefaultWebAppConfiguration.MEDIA_IMAGE_PATH + "/" + f.getName() + "_BIG");

		if (f.createNewFile())
			ImageData.createThumbnail(new String(Base64.decodeBase64(src)), f);

		if (flarge.createNewFile())
			ImageData.createLargeImage(new String(Base64.decodeBase64(src)), flarge);

		UserPhotoElement up = new UserPhotoElement(id, name, userId , f.getName());
		IndexResponse ir = (IndexResponse) esContainer
				.executeSynchronusRequest(new DefaultDataElement<UserPhotoElement>(up)
						.setIndex(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO));
		up.setDocumentId(ir.getId());
		return up;
	}

	@RequestMapping(value = { "/media/create" }, method = { RequestMethod.POST })
	public @ResponseBody Object create(
			@RequestParam(name = "name", required = false, defaultValue = "Untitled") String name,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model)
			throws IOException {
		UserMediaElement ume = new UserMediaElement();
		ume.setName(name);
		ume.setUserId( userId );
		IndexResponse ir = (IndexResponse) esContainer
				.executeSynchronusRequest(new DefaultDataElement<UserMediaElement>(ume)
						.setIndex(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_INDEX));
		ume.setDocumentId(ir.getId());
		return ume;
	}

	@RequestMapping(path = "/profile/update", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object updatePicture(
			@RequestParam(name = "picture", required = true) String base64EncodedImageData, HttpServletRequest request,
			HttpServletResponse response, SessionStatus status, Model model) {

		try {
			User current = userService.findById( userId );
			File f = ImageData.randomFileName(DefaultWebAppConfiguration.USER_IMAGE_PATH);

			if (f.createNewFile()) {

				if (current.getPicture() != null)
					new File(DefaultWebAppConfiguration.USER_IMAGE_PATH + "/" + current.getPicture()).delete();

				File profile = new File(DefaultWebAppConfiguration.USER_IMAGE_PATH + "/" + current.getUserId());

				if (!profile.exists())
					profile.mkdir();

				File profilePicture = new File(
						DefaultWebAppConfiguration.USER_IMAGE_PATH + "/" + current.getUserId() + "/thumbnail");

				if (profilePicture.exists())
					profilePicture.delete();

				ImageData.createThumbnail(base64EncodedImageData, f);
				ImageData.createThumbnail(base64EncodedImageData, profilePicture);
			}

			current.setPicture(f.getName());
			userService.save(current);
			return StatusResponse.success();
		} catch (IOException e) {
			e.printStackTrace();
			return StatusResponse.error(e);
		}

	}

	@RequestMapping(path = "/profile/view", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object view(@RequestParam(name = "uid", required = true) Long uid, HttpServletRequest request,
			HttpServletResponse response, SessionStatus status, Model model) {
		return new UserPublicFormElement<User>(userService.findById(uid));
	}
	
    @RequestMapping(path = "/profile", headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	return new ModelAndView("forward:/user/" + userSession.userId() + "/profile");
    }

}
