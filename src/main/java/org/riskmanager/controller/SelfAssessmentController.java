package org.riskmanager.controller;


import org.apache.log4j.Logger;
import org.riskmanager.domain.chapters.EV1Model;
import org.riskmanager.service.EV1ModelService;
import org.riskmanager.service.EV1ValueFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

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


    @Resource(name = "ev1ModelService")
    EV1ModelService ev1ModelService;

    Logger logger = Logger.getLogger("controller");


    @ModelAttribute("ev1ValueFactory")
    public EV1ValueFactory getEV1ValueFactory(){
        return new EV1ValueFactory();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSelfAssessmentMainPage(Model model){

        //priority todo show select saved EVALUATION page
        return "self_assess/main_page";
    }


    @RequestMapping(value = "/ev1", method = RequestMethod.GET)
    public String getEV1Page(Model model){

        model.addAttribute("ev1Model", new EV1Model());

        return "self_assess/ev1_page";
    }

    @RequestMapping(value = "/ev1", method = RequestMethod.POST)
    public String add(@ModelAttribute("ev1Model") EV1Model ev1Model) {


        //todo: priority it is now known, that it can't save null ev1GroupParameter
        //priority: you should update everyting on submit

        logger.debug("ev1MODEL::::"+ev1Model.getEv1value()+ev1Model.getmGroupValues());

        ev1ModelService.add(ev1Model);

        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")




}
