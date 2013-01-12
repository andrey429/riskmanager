package org.main;

import org.riskassess.controller.RiskAssessController;
import org.riskassess.domain.basic.AssetType;
import org.riskassess.domain.basic.MediaType;
import org.riskassess.domain.complex.RiskDetail;
import org.riskassess.domain.complex.ScopeObject;
import org.riskassess.factories.RiskValueFactory;
import org.riskassess.factories.ThreatSourceFactory;
import org.riskassess.service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 11.01.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/app")
public class SuperMainController{




    @RequestMapping(value = "/")
    public String getMain(){
        return "mainmenu";
    }




}
