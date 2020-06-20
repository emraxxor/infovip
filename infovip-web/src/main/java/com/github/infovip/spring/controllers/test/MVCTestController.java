package com.github.infovip.spring.controllers.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.github.infovip.core.web.response.datatable.DataTableManager;
import com.github.infovip.core.web.response.datatable.DataTableResult;
import com.github.infovip.test.datatable.TableTest;

@Controller
@RequestMapping("/springtest")
public class MVCTestController {

	@Autowired
    private ApplicationContext appContext;
    
    @PersistenceContext(unitName = "infovipPU")
    private EntityManager em;
    

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
    }

    @RequestMapping(path = "/datatable", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DataTableResult processSubmit(HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
        return DataTableManager.create(new TableTest(em, request, response)).pageable(PageRequest.of(0, 50)).result();
    }

}
