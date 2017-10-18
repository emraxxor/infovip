package com.github.infovip.spring.controllers.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.core.lang.Translate;

@Controller
@RequestMapping("/core/language")
public class LanguageController {
	
    @RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object language(HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		return Translate.properties();
	}
}
