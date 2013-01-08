package org.riskassess.controller;

import org.riskassess.domain.basic.AssetType;
import org.riskassess.domain.basic.MediaType;
import org.riskassess.domain.complex.RiskDetail;
import org.riskassess.domain.complex.ScopeObject;
import org.riskassess.factories.ThreatSourceFactory;
import org.riskassess.service.HibernateService;
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

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/risk-assessment")
public class RiskAssessController {


    /*@RequestMapping(value = "/create", method = RequestMethod.GET)*/
    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    @Resource
    HibernateService hibernateService;


    @ModelAttribute("storedAssetTypes")
    public List<AssetType> getStoredAssetTypes() {
        return hibernateService.getAllAssetTypes();
    }

    @ModelAttribute("storedMediaTypes")
    public List<MediaType> getMediaTypes() {
        return hibernateService.getAllMediaTypes();
    }

    @ModelAttribute("threatSourceFactory")
    public ThreatSourceFactory getThreatSourceFactory(){
        return new ThreatSourceFactory(messageSource);
    }
    /*menues*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainMenu() {
        return "risk_assess/main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreateMenu() {
        return "risk_assess/create/create_menu";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String getShowMenu() {
        return "risk_assess/show/show_menu";
    }


    /*asset types*/
    /*//create*/

    @RequestMapping(value = "/create/asset-type", method = RequestMethod.GET)
    public String getCreateAssetTypePage(Model model) {

        model.addAttribute("assetType", new AssetType());
        return "risk_assess/description-forms/asset_type_description";
    }


    @RequestMapping(value = "/create/asset-type", method = RequestMethod.POST)
    public String saveCreatedAssetAndGetBack(Model model,
                                             @ModelAttribute("assetType") AssetType assetType) {

        hibernateService.addAssetType(assetType);
        return "redirect:/riskmanager/risk-assessment/create";
    }

    /*//edit*/

    @RequestMapping(value = "/edit/asset-type", method = RequestMethod.GET)
    public String getEditAssetTypePage(Model model,
                                       @RequestParam(value = "assetTypeID", required = true) Integer assetTypeID) {
        AssetType assetType = hibernateService.getAssetType(assetTypeID);
        if (assetType == null) {
            return "errorpage";
        }


        model.addAttribute("assetType", assetType);
        return "risk_assess/description-forms/asset_type_description";
    }

    @RequestMapping(value = "edit/asset-type", method = RequestMethod.POST)
    public String saveEditedAssetAndGetBack(Model model,
                                            @ModelAttribute("assetType") AssetType assetType,
                                            @RequestParam(value = "assetTypeID", required = true) Integer assetTypeID) {

        assetType.setId(assetTypeID);
        hibernateService.editAssetType(assetType);
        return "redirect:/riskmanager/risk-assessment/create";
    }

    /*media objects*/

     /*//create*/

    @RequestMapping(value = "/create/media-type", method = RequestMethod.GET)
    public String getCreateMediaTypePage(Model model) {

        model.addAttribute("mediaType", new MediaType());
        return "risk_assess/description-forms/media_type_description";
    }


    @RequestMapping(value = "/create/media-type", method = RequestMethod.POST)
    public String saveCreatedMediaAndGetBack(Model model,
                                             @ModelAttribute("mediaType") MediaType mediaType) {

        hibernateService.addMediaType(mediaType);
        return "redirect:/riskmanager/risk-assessment/create";
    }



    /*//edit*/

    @RequestMapping(value = "/edit/media-type", method = RequestMethod.GET)
    public String getEditMediaTypePage(Model model,
                                       @RequestParam(value = "mediaTypeID", required = true) Integer mediaTypeID) {
        MediaType mediaType = hibernateService.getMediaType(mediaTypeID);
        if (mediaType == null) {
            return "errorpage";
        }


        model.addAttribute("mediaType", mediaType);
        return "risk_assess/description-forms/media_type_description";
    }

    @RequestMapping(value = "edit/media-type", method = RequestMethod.POST)
    public String saveEditedMediaAndGetBack(Model model,
                                            @ModelAttribute("mediaType") MediaType mediaType,
                                            @RequestParam(value = "mediaTypeID", required = true) Integer mediaTypeID) {

        mediaType.setId(mediaTypeID);
        hibernateService.editMediaType(mediaType);
        return "redirect:/riskmanager/risk-assessment/create";
    }


    /*//risk evaluations*/

    @RequestMapping(value = "/create/risk", method = RequestMethod.GET)
    public String getCreateRiskPage(Model model) {
        model.addAttribute("scopeObject", new ScopeObject());
        model.addAttribute("riskDetail", new RiskDetail());

        return "risk_assess/description-forms/risk";

    }


}
