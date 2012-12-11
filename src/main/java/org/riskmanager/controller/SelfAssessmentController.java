package org.riskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public String getSelfAssessmentMainPage(){
        return "self_assess/main_page";
    }

    //recuest parameter - saved or new self-assessment
    //@RequestMapping("/page-m1")

}
