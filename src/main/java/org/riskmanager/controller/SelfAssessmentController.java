package org.riskmanager.controller;


import org.apache.log4j.Logger;
import org.riskmanager.domain.chapters.M1ChapterModel;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSelfAssessmentMainPage(Model model){


        return "self_assess/main_page";
    }


    @RequestMapping(value = "/m1", method = RequestMethod.GET)
    public String getM1Page(Model model){
        M1ChapterModel m1ChapterModel = new M1ChapterModel();
        model.addAttribute("m1ChapterModel", m1ChapterModel);
        return "self_assess/page-m1";
    }

    @RequestMapping(value = "/m1", method = RequestMethod.POST)
    public String add(@ModelAttribute("m1ChapterModel") M1ChapterModel m1ChapterModel) {


        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name

        // Call PersonService to do the actual adding


        // This will resolve to /WEB-INF/jsp/person_addedpage.jsp
        logger.debug("is : : :: " + m1ChapterModel.getMap().get(0));

        return "redirect:/riskmanager/self-assessment/";
    }


    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")

}
