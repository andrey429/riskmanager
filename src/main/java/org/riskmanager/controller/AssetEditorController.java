package org.riskmanager.controller;

import org.apache.log4j.Logger;

import org.riskmanager.domain.Asset;
import org.riskmanager.service.AssetService;

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
 * Date: 08.10.12
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/assets")
public class AssetEditorController {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "assetService")
    private AssetService assetService;

    @RequestMapping("/list")
    public String  getAssets(Model model){
        logger.debug("Received request to list assets");

        List<Asset> assets = assetService.getAll();

        model.addAttribute("assets", assets);

        return "assets_views/assets_list_page";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model){
        logger.debug("Received request to view add page");

        model.addAttribute("assetAttribute", new Asset());

        return "assets_views/asset_addpage";


    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("assetAttribute") Asset asset)
    {
        logger.debug("Received request to add asset");

        assetService.add(asset);
        return "assets_views/asset_addedpage";

    }

    /*@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Integer id, Model model){
        logger.debug("Received  request to delete organisation");
        organisationService.delete(id);
        model.addAttribute("id", id);
        return "organisations_views/organisation_deletedpage";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) Integer id, Model model){
        logger.debug("Received request to view edit page");

        model.addAttribute("organisationAttribute", organisationService.get(id));

        return "organisations_views/organisation_editpage";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "organisationAttribute") Organisation organisation,
                       @RequestParam(value = "id", required =true) Integer id, Model model ){
        logger.debug("Received request to update organisation");

        organisation.setId(id);
        organisationService.edit(organisation);
        model.addAttribute("id", id);//todo it is REALLY UNNECESSARY NOW
        return "organisations_views/organisation_editedpage";

    }
*/

}
