package org.riskassess.controller;

import org.apache.log4j.Logger;
import org.riskassess.converters.AssetTypePropertyEditor;
import org.riskassess.converters.MediaTypePropertyEditor;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    Logger logger = Logger.getLogger("controller");

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
    public List<MediaType> getStoredMediaTypes() {
        return hibernateService.getAllMediaTypes();
    }

    @ModelAttribute("storedScopeObjects")
    public List<ScopeObject> getStoredScopeObjects() {
        return hibernateService.getAllScopeObjects();
    }

    @ModelAttribute("storedRiskDetails")
    public List<RiskDetail> getStoredRiskDetails() {
        return hibernateService.getAllRiskDetails();
    }

    @ModelAttribute("threatSourceFactory")
    public ThreatSourceFactory getThreatSourceFactory() {
        return new ThreatSourceFactory(messageSource);
    }

    @ModelAttribute("riskValueFactory")
    public RiskValueFactory getRiskValueFactory() {
        return new RiskValueFactory(messageSource);
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

        return "risk_assess/show/show_assessment";
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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditPage() {
        return "risk_assess/edit-menu/edit-menu";
    }

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

    @RequestMapping(value = "/create/scope", method = RequestMethod.GET)
    public String getCreateScopePage(Model model) {
        model.addAttribute("scopeObject", new ScopeObject());


        return "risk_assess/description-forms/scope_object";

    }

    @RequestMapping(value = "/create/scope", method = RequestMethod.POST)
    public String saveCreatedScopeAndGotoRisk(Model model,
                                              @ModelAttribute("scopeObject") ScopeObject scopeObject
    ) {

        if (hibernateService.getScopeObjectByAssetAndMedia(scopeObject.getAssetType().getId(), scopeObject.getMediaType().getId()) == null) {
            hibernateService.addScopeObject(scopeObject);
        }


        return "redirect:/riskmanager/risk-assessment/create/risk?assetTypeID=" + scopeObject.getAssetType().getId()
                + "&mediaTypeID=" + scopeObject.getMediaType().getId();
    }

    /*@RequestMapping(value = "/create/risk", method = RequestMethod.GET)*/

    @RequestMapping(value = "/create/risk")
    public String getCreateRiskPage(Model model/*,
                                    @RequestParam(value = "assetTypeID", required = true) Integer assetTypeID,
                                    @RequestParam(value = "mediaTypeID", required = true) Integer mediaTypeID*/) {
        /*AssetType assetType = hibernateService.getAssetType(assetTypeID);
        MediaType mediaType = hibernateService.getMediaType(mediaTypeID);*/
        /*List<RiskDetail> associatedRisks = hibernateService.getRiskDetailsByAssetAndMedia(assetTypeID, mediaTypeID);*/


        /*model.addAttribute("selectedAssetTypeModel", assetType);
        model.addAttribute("selectedMediaTypeModel", mediaType);
        model.addAttribute("associatedRisks", associatedRisks);*/
        model.addAttribute("riskIDAttribute", null);
        model.addAttribute("newRisk", new RiskDetail());
        return "risk_assess/description-forms/risk_detail_description";
    }

    @RequestMapping(value = "/edit/risk", method = RequestMethod.GET)
    public String getExistingRiskPage(Model model,
                                      @RequestParam(value = "riskID", required = true) Integer riskID) {

        RiskDetail riskDetail = hibernateService.getRiskDetail(riskID);
        if (riskDetail == null) return "errorpage";

        model.addAttribute("newRisk", riskDetail);
        model.addAttribute("riskIDAttribute", riskID);

        return "risk_assess/description-forms/risk_detail_description";
    }

    @RequestMapping(value = "/edit/risk", method = RequestMethod.POST)
    public String saveExistingRisk(Model model,
                                   @RequestParam(value = "riskID", required = true) Integer riskID,
                                   @ModelAttribute(value = "newRisk") RiskDetail riskDetail) {

        hibernateService.editRiskDetail(riskDetail);

        return "redirect:/riskmanager/risk-assessment/create/";

    }


    @RequestMapping(value = "/create/risk", method = RequestMethod.POST)
    public String saveCreatedRiskAndGoto(Model model,
                                         @RequestParam(value = "assetTypeID", required = true) Integer assetTypeID,
                                         @RequestParam(value = "mediaTypeID", required = true) Integer mediaTypeID,
                                         @ModelAttribute("newRisk") RiskDetail newRisk) {


        List<RiskDetail> listOfAssociatedRisks;
        ScopeObject scopeObject = hibernateService.getScopeObjectByAssetAndMedia(assetTypeID, mediaTypeID);

        AssetType assetType = hibernateService.getAssetType(assetTypeID);
        MediaType mediaType = hibernateService.getMediaType(mediaTypeID);


        if (scopeObject == null) {
            scopeObject = new ScopeObject();
            listOfAssociatedRisks = new ArrayList<RiskDetail>();
            listOfAssociatedRisks.add(newRisk);

            scopeObject.setRiskDetails(listOfAssociatedRisks);
            scopeObject.setMediaType(mediaType);
            scopeObject.setAssetType(assetType);

            newRisk.setId(null);//todo

            hibernateService.addRiskDetail(newRisk);
            hibernateService.addScopeObject(scopeObject);

        } else {
            listOfAssociatedRisks = scopeObject.getRiskDetails();
            if (listOfAssociatedRisks == null) {
                listOfAssociatedRisks = new ArrayList<RiskDetail>();
            }
            listOfAssociatedRisks.add(newRisk);
            //save riskdetail
            newRisk.setId(null);//todo
            hibernateService.addRiskDetail(newRisk);
            scopeObject.setRiskDetails(listOfAssociatedRisks);
            hibernateService.editScopeObject(scopeObject);
        }


        return "redirect:/riskmanager/risk-assessment/create/";


    }


    /*delete risks*/


    @RequestMapping(value = "/delete/risk", method = RequestMethod.GET)
    public String getDeleteRiskPage(Model model,
                                    @RequestParam(value = "assetTypeID", required = true) Integer assetTypeID,
                                    @RequestParam(value = "mediaTypeID", required = true) Integer mediaTypeID,
                                    @RequestParam(value = "riskID", required = true) Integer riskID) {

        //todo no checks made - NOT SAFE
        ScopeObject scopeObject = hibernateService.getScopeObjectByAssetAndMedia(assetTypeID, mediaTypeID);
        List<RiskDetail> riskDetailList = scopeObject.getRiskDetails();

        RiskDetail riskDetailFromList = null;
        for (RiskDetail r : riskDetailList) {
            if (r.getId().compareTo(riskID) == 0) {
                riskDetailFromList = r;
                break;
            }
        }

        if (riskDetailFromList == null) return "errorpage";

        riskDetailList.remove(riskDetailFromList);
        scopeObject.setRiskDetails(riskDetailList);
        hibernateService.editScopeObject(scopeObject);

        hibernateService.deleteRiskDetail(riskID);
        return "redirect:/riskmanager/risk-assessment/show";

    }

    /*property editors binders*/


    @InitBinder
    public void initMediaTypeIDEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(MediaType.class, new MediaTypePropertyEditor(hibernateService));
    }

    @InitBinder
    public void initAssetTypeIDEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(AssetType.class, new AssetTypePropertyEditor(hibernateService));
    }


}
