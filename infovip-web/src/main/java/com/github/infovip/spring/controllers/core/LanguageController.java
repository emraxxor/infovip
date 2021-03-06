package com.github.infovip.spring.controllers.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.core.lang.Translate;

@RestController
@RequestMapping("/core/language")
public class LanguageController {
	
    @GetMapping
	public Object language(HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		return Translate.properties();
	}
}
