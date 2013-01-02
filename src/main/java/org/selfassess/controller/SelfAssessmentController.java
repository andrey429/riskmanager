package org.selfassess.controller;


import org.apache.log4j.Logger;
import org.selfassess.domain.EV1Model;
import org.selfassess.fbo.DomainToFboIO;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.service.EV1ModelService;
import org.selfassess.service.EV1ValueFactory;
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


        return "self_assess/main_page";
    }


    @RequestMapping(value = "/ev1", method = RequestMethod.GET)
    public String getEV1Page(Model model){

        EV1FormBackingObject ev1FBO = new EV1FormBackingObject();
        model.addAttribute("ev1FBO", ev1FBO);

        return "self_assess/ev1_page";
    }

    @RequestMapping(value = "/ev1", method = RequestMethod.POST)
    public String add(@ModelAttribute("ev1FBO") EV1FormBackingObject ev1FBO) {


        DomainToFboIO io = new DomainToFboIO();
        EV1Model ev1Model = io.convertToDomainObject(ev1FBO);

        /*logger.debug("ev1MODEL::::"+ ev1Model.getmGroupValues());*/
        logger.debug("EV1: "+ev1FBO.getmGroupValues());
        logger.debug("EV1: "+ev1FBO.getEv1Value());
        logger.debug("PARAML: "+ev1FBO.getParameterValues());

        ev1ModelService.add(ev1Model);





        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")




}
