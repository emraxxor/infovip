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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.github.infovip.beans.stateless.timeline.TimeLineManagement;
import com.github.infovip.beans.stateless.timeline.TimeLineManagementLocal;
import com.github.infovip.core.Configuration;
import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;
import com.github.infovip.spring.elasticsearch.entities.TimelineCommentEntity;
import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;


/**
 *
 * @author attila
 */
@Controller
@RequestMapping("/timeline")
public class TimelineController {

    @Autowired
    private ApplicationContext appContext;
    
    private TimeLineManagementLocal timeLineManagement = lookupTimeLineManagementLocal();

    @Autowired 
    private DefaultElasticsearchTemplate eTemplate;



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
        TimelinePostEntity post = new TimelinePostEntity(
                new DateTime().toDate(),
                30L,
                "UserName",
                "Message......",
                "simple"
        );

        timeLineManagement.save(post);

        IndexQuery q = new IndexQuery();
        TimelineCommentEntity comment = new TimelineCommentEntity(
                post.getId(),
                new DateTime().toDate(),
                30L,
                "user",
                "comment",
                "simple"
        );

        q.setParentId(post.getId());
        q.setObject(comment);
        eTemplate.index(q);

        m.addAttribute("post", timeLineManagement.findAll());
        return "core/timeline/Timeline";
    }
    
    private TimeLineManagementLocal lookupTimeLineManagementLocal() {
        try {
            Context c = new InitialContext();
            return (TimeLineManagementLocal) c.lookup(Configuration.jndiLookupName(TimeLineManagement.class.getSimpleName()));
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
