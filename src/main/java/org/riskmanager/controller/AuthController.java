package org.riskmanager.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 13.10.12
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
                               ModelMap modelMap){
        logger.debug("request to show login page");
        if(error == true){
            modelMap.put("error", "Wrong username or password");
        }
        else {
            modelMap.put("error", "");
        }


        return "login";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String getAccessDeniedPage(){
        logger.debug("request to show access denied page");
        return "denied";
    }
}
