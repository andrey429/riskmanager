package org.riskmanager.controller;

import org.apache.log4j.Logger;
import org.riskmanager.converters.PersonPropertyEditor;
import org.riskmanager.domain.Asset;
import org.riskmanager.domain.Person;
import org.riskmanager.service.AssetService;
import org.riskmanager.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "assetService")
    private AssetService assetService;

    @Resource(name = "personService")
    private PersonService personService;


    @RequestMapping("/")
    public String getAssets(Model model) {
        logger.debug("Received request to list assets");

        Person currentUser = getLoggedInAuthority();

        List<Asset> assets;

        if (currentUser.isAdmin()) {
            assets = assetService.getAll();
        } else {
            assets = assetService.getAllByPersonOrganisation(currentUser);
        }


        model.addAttribute("assets", assets);

        return "assets_views/assets_list_page";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to view add page");

        model.addAttribute("assetAttribute", new Asset());

        return "assets_views/asset_addpage";


    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("assetAttribute") Asset asset) {
        logger.debug("Received request to add asset");

        assetService.add(asset);
        return "redirect:/riskmanager/assets/";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.debug("Received  request to delete organisation");
        assetService.delete(id);
        model.addAttribute("id", id);
        return "redirect:/riskmanager/assets/";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.debug("Received request to view edit page");

        model.addAttribute("assetAttribute", assetService.get(id));

        return "assets_views/asset_editpage";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "assetAttribute") Asset asset,
                       @RequestParam(value = "id", required = true) Integer id, Model model) {
        logger.debug("Received request to saveOrUpdate asset");

        asset.setId(id);
        assetService.edit(asset);

        return "redirect:/riskmanager/assets/";

    }






    @ModelAttribute("existingPersons")
    public List<Person> getExistingPersons() {
        return personService.getAll();

    }

    @InitBinder
    public void initPersonPropertyEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Person.class, new PersonPropertyEditor(this.personService));
    }


    @ModelAttribute("loggedInAuthority")
    public Person getLoggedInAuthority() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String authorityLogin = auth.getName();
        return personService.getPersonByLogin(authorityLogin);
    }


}
