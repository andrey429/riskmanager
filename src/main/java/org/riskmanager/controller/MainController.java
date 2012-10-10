package org.riskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/")
    public String showMainPage(){
        return "main_views/main_view";
    }



}
