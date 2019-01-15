/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.spring.controllers.timeline;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author attila
 */
@Controller
@RequestMapping("/timeline")
public class TimelineController {

    @Autowired
    private ApplicationContext appContext;


    @Autowired 
    private ElasticsearchTemplate template;
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
    }

    /**
     * Simple test...
     *
     * @param locale
     * @param request
     * @param response
     * @param result
     * @param status
     * @param model
     * @return
     */
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addTestDocument(Locale locale, HttpServletRequest request, HttpServletResponse response, Model m) {
        return null;
    }

}
