package org.riskmanager.controller;


import org.apache.log4j.Logger;
import org.riskmanager.domain.chapters.EV1Model;
import org.riskmanager.service.EV1ValueFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @ModelAttribute("ev1ValueFactory")
    public EV1ValueFactory getEV1ValueFactory(){
        return new EV1ValueFactory();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSelfAssessmentMainPage(Model model){


        return "self_assess/main_page";
    }


    @RequestMapping(value = "/ev1", method = RequestMethod.GET)
    public String getEV1Page(Model model){
        EV1Model ev1Model = new EV1Model();
        model.addAttribute("ev1Model", ev1Model);

        return "self_assess/ev1_page";
    }

    @RequestMapping(value = "/ev1", method = RequestMethod.POST)
    public String add(@ModelAttribute("ev1Model") EV1Model ev1Model) {


        //todo: stub

        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")




}
