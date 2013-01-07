package org.selfassess.controller;


import org.apache.log4j.Logger;
import org.selfassess.domain.EV1Model;
import org.selfassess.domain.SelfAssessmentModel;
import org.selfassess.reports.SelfAssessmentReportBuilder;
import org.selfassess.service.SelfAssessmentModelService;
import org.selfassess.utils.POJO2FBOConverter;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.service.EV1ModelService;
import org.selfassess.utils.EV1ValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

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

    /*private SelfAssessmentModel currentAssessment;*///todo stub for storing current assessment
    //todo: works when 1 user edits assessment, guess. Use WebFlow instead


    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    @Resource(name = "ev1ModelService")
    EV1ModelService ev1ModelService;
    @Resource(name = "selfAssessmentModelService")
    SelfAssessmentModelService selfAssessmentModelService;


    Logger logger = Logger.getLogger("controller");

    @ModelAttribute("storedAssessments")
    public List<SelfAssessmentModel> getStoredAssessments() {
        return selfAssessmentModelService.getAll();
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
        return "self_assess/assessment_description";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String continueFromNewToMenuPage(Model model, @ModelAttribute("selfAssessmentModel")
    SelfAssessmentModel currentAssessment) {
        selfAssessmentModelService.add(currentAssessment);
        return "redirect:/riskmanager/self-assessment/menu?assessmentID=" + currentAssessment.getId();
    }

    //after /new -> post a form with selfAssessmentModel -> /new/menu


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String showMenuPage(Model model, @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {
        SelfAssessmentModel currentAssessment = selfAssessmentModelService.get(assessmentID);

        logger.debug("currentassessment is " + currentAssessment);

        model.addAttribute("selfAssessmentModel", currentAssessment);

        return "self_assess/assessment_menu";
    }


    @RequestMapping(value = "/menu/ev1", method = RequestMethod.GET)
    public String getEV1Page(Model model,
                             @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {

        EV1FormBackingObject ev1FBO;

        EV1Model ev1Model = ev1ModelService.getBySelfAssessmentID(assessmentID);

        if (ev1Model == null) {
            ev1FBO = new EV1FormBackingObject();
        } else {
            ev1FBO = new POJO2FBOConverter().convertToFormBackingObject(ev1Model);
        }

        /*logger.debug("new model is "+ev1Model.toString());*/

        model.addAttribute("ev1FBO", ev1FBO);
        model.addAttribute("assessmentID", assessmentID);

        return "self_assess/ev1pages/ev1_page";
    }

    @RequestMapping(value = "/menu/ev1", method = RequestMethod.POST)
    public String add(@ModelAttribute("ev1FBO") EV1FormBackingObject ev1FBO,
                      @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {

        POJO2FBOConverter io = new POJO2FBOConverter();
        EV1Model ev1Model = io.convertToDomainObject(ev1FBO);
        SelfAssessmentModel currentAssessment = selfAssessmentModelService.get(assessmentID);


        EV1Model storedInDBModel = ev1ModelService.getBySelfAssessmentID(assessmentID);

        if (storedInDBModel != null) {
            ev1Model.setId(storedInDBModel.getId());//replace ID
            ev1ModelService.edit(ev1Model);
        } else {
            ev1ModelService.add(ev1Model);

        }

        currentAssessment.setEv1Model(ev1Model);
        selfAssessmentModelService.edit(currentAssessment);

        return "redirect:/riskmanager/self-assessment/";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditExistingAssessment(@RequestParam(value = "assessmentID", required = true) Integer assessmentID,
                                            Model model) {
        SelfAssessmentModel selfAssessmentModel = selfAssessmentModelService.get(assessmentID);
        if (selfAssessmentModel == null) {
            return "errorpage";
        }
        model.addAttribute("selfAssessmentModel", selfAssessmentModel);
        return "self_assess/assessment_description";

    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String continueFromEditToMenuPage(Model model,
                                             @ModelAttribute("selfAssessmentModel") SelfAssessmentModel currentAssessment,
                                             @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {

        currentAssessment.setId(assessmentID);
        selfAssessmentModelService.edit(currentAssessment);

        return "redirect:/riskmanager/self-assessment/menu?assessmentID=" + currentAssessment.getId();
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String getShowPage(Model model,
                              @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {

        SelfAssessmentModel selfAssessmentModel = selfAssessmentModelService.get(assessmentID);
        if (selfAssessmentModel == null) {
            return "redirect:/riskmanager/self-assessment/error?code=1";
        }

        EV1Model ev1Model = selfAssessmentModel.getEv1Model();
        if (ev1Model == null) {
            return "redirect:/riskmanager/self-assessment/error?code=1";
        }
        EV1FormBackingObject ev1FBO = new POJO2FBOConverter().convertToFormBackingObject(ev1Model);

        model.addAttribute("selfAssessmentModel", selfAssessmentModel);
        model.addAttribute("ev1FBO", ev1FBO);

        return "self_assess/diagram";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage(Model model, @RequestParam(value = "code", required = true) Integer code) {

        model.addAttribute("code", code);


        return "errorpage";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String getDownload(Model model, @RequestParam(value = "assessmentID", required = true) Integer assessmentID) {
        SelfAssessmentModel selfAssessmentModel = selfAssessmentModelService.get(assessmentID);
        if (selfAssessmentModel == null)
            return "redirect:/riskmanager/self-assessment/error?code=1";

        SelfAssessmentReportBuilder reportBuilder = new SelfAssessmentReportBuilder(messageSource);
        reportBuilder.exportSelfAssessmentToDocxFile(selfAssessmentModel, null, "file.docx");

        /*logger.debug("meessage sors: " + messageSource.getMessage("report.title", null, Locale.getDefault()));*/

        return "redirect:/riskmanager/self-assessment/";
    }

}
