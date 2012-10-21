package org.riskmanager.controller;


import org.riskmanager.domain.Person;

import org.riskmanager.reports.RiskEvaluationReportBuilder;
import org.riskmanager.service.AssetService;
import org.riskmanager.service.PersonService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 09.10.12
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/")
public class MainController {

    @Resource
    private PersonService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage() {

        return "main_views/main_view";
    }




    @ModelAttribute("loggedInUsername")
    public String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @ModelAttribute("loggedInFullName")
    public String getLoggedInFullName() {
        Person loggedInUser = service.getPersonByLogin(getLoggedInUsername());
        return loggedInUser.getLastName() + " " + loggedInUser.getFirstName();
    }

}
