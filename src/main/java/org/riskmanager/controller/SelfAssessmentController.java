package org.riskmanager.controller;


import org.apache.log4j.Logger;
import org.riskmanager.converters.GroupParameterPropertyEditor;
import org.riskmanager.domain.chapters.M1Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 11.12.12
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/self-assessment")
public class SelfAssessmentController  {

    Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSelfAssessmentMainPage(Model model){


        return "self_assess/main_page";
    }


    @RequestMapping(value = "/m1", method = RequestMethod.GET)
    public String getM1Page(Model model){
        M1Model m1 = new M1Model();
        model.addAttribute("m1Model", m1);
        return "self_assess/page-m1";
    }

    @RequestMapping(value = "/m1", method = RequestMethod.POST)
    public String add(@ModelAttribute("m1Model") M1Model m1) {



        //todo: stub

        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")




}
