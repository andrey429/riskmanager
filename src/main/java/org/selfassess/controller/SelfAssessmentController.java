package org.selfassess.controller;


import org.apache.log4j.Logger;
import org.selfassess.domain.EV1Model;
import org.selfassess.domain.SelfAssessmentModel;
import org.selfassess.service.SelfAssessmentModelService;
import org.selfassess.utils.POJO2FBOConverter;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.service.EV1ModelService;
import org.selfassess.utils.EV1ValueFactory;
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
public class SelfAssessmentController {

    private SelfAssessmentModel currentAssessment;//thing to store parent assessment object


    @Resource(name = "ev1ModelService")
    EV1ModelService ev1ModelService;
    @Resource(name = "selfAssessmentModelService")
    SelfAssessmentModelService selfAssessmentModelService;


    Logger logger = Logger.getLogger("controller");

    @ModelAttribute("currentAssessment")
    public SelfAssessmentModel getCurrentAssessment() {
        return currentAssessment;
    }


    @ModelAttribute("ev1ValueFactory")
    public EV1ValueFactory getEV1ValueFactory() {
        return new EV1ValueFactory();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSelfAssessmentMainPage(Model model) {


        return "self_assess/main_page";
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewPage(Model model) {
        model.addAttribute("selfAssessmentModel", new SelfAssessmentModel());
        return "self_assess/new_assessment_page";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String continueFromNewToMenuPage(Model model, @ModelAttribute("selfAssessmentModel")
    SelfAssessmentModel currentAssessment) {

        this.currentAssessment = currentAssessment;
        return "redirect:new/menu";
    }

    //after /new -> post a form with selfAssessmentModel -> /new/menu


    @RequestMapping(value = "/new/menu", method = RequestMethod.GET)
    public String showMenuPage(Model model) {
        return "self_assess/new_assessment_menu";
    }


    @RequestMapping(value = "/new/menu/ev1", method = RequestMethod.GET)
    public String getEV1Page(Model model) {

        EV1FormBackingObject ev1FBO = new EV1FormBackingObject();
        model.addAttribute("ev1FBO", ev1FBO);

        return "self_assess/ev1_page";
    }

    @RequestMapping(value = "/new/menu/ev1", method = RequestMethod.POST)
    public String add(@ModelAttribute("ev1FBO") EV1FormBackingObject ev1FBO) {


        POJO2FBOConverter io = new POJO2FBOConverter();
        EV1Model ev1Model = io.convertToDomainObject(ev1FBO);
        getCurrentAssessment().setEv1Model(ev1Model);
        ev1ModelService.add(ev1Model);
        selfAssessmentModelService.add(getCurrentAssessment());

        /*EV1Model test = ev1ModelService.get(1);

        EV1FormBackingObject fbo = io.convertToFormBackingObject(ev1Model);
        logger.debug("EV1 group: " + fbo.getmGroupValues());
        logger.debug("EV1 param: " + fbo.getParameterValues());
        logger.debug("EV1 val: " + fbo.getEv1Value());*/


        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")


}
