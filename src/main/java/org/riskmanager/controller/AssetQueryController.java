package org.riskmanager.controller;

import org.apache.log4j.Logger;
import org.riskmanager.converters.OrganisationPropertyEditor;
import org.riskmanager.converters.PersonPropertyEditor;
import org.riskmanager.domain.Asset;
import org.riskmanager.domain.AssetQuery;
import org.riskmanager.domain.Organisation;
import org.riskmanager.domain.Person;
import org.riskmanager.reports.RiskEvaluationReportBuilder;
import org.riskmanager.service.AssetService;
import org.riskmanager.service.OrganisationService;
import org.riskmanager.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * Date: 20.10.12
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class AssetQueryController {

    protected static Logger logger = Logger.getLogger("service");
    @Resource
    private PersonService personService;
    @Resource
    private OrganisationService organisationService;
    @Resource(name = "assetService")
    private AssetService assetService;
    private List<Asset> queriedAssetsList;

    private AssetQuery assetQuery = new AssetQuery();

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String getQuery(Model model) {
        logger.debug("showing assets query page");

        //
        //

        model.addAttribute("queryAttribute", assetQuery != null ? assetQuery : new AssetQuery());//todo change to new AQ()
        model.addAttribute("existingPersons", getExistingPersons());
        model.addAttribute("existingOrganisations", getExistingOrganisations());
        model.addAttribute("queriedAssetsList", queriedAssetsList);

        return "assets_views/asset_querypage";
    }

    @RequestMapping(value = "/query/reset", method = RequestMethod.GET)
    public String resetQuery(Model model) {
        assetQuery = null;
        queriedAssetsList = null;

        return "redirect:/riskmanager/query";

    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String postQueryResults(@ModelAttribute("queryAttribute") AssetQuery assetQuery, Model model) {

        logger.debug("ASSET PERSON: " + assetQuery.getPerson());
        logger.debug("ASSET fds: " + assetQuery.getRequiresAvailability());
        logger.debug("query object" + assetQuery);

        this.assetQuery = assetQuery;


        StringBuffer customQuery = new StringBuffer("FROM Asset");
        ArrayList<String> conditions = new ArrayList<String>();//gather conditions together

        if (assetQuery.getOrganisation() != null) {
            conditions.add("a.personOwner.organisation.id = " + assetQuery.getOrganisation().getId());
        }


        if (assetQuery.getPerson() != null) {
            conditions.add("a.personOwner.id = " + assetQuery.getPerson().getId());
        }
        if (assetQuery.getBusinessProcessType() != null) {
            conditions.add("a.businessProcessType = " + assetQuery.getBusinessProcessType());
        }
        if (assetQuery.getRequiresConfidentiality()) {
            conditions.add("a.requiresConfidentiality = 1");
        }
        if (assetQuery.getRequiresIntegrity()) {
            conditions.add("a.requiresIntegrity = 1");
        }
        if (assetQuery.getRequiresAvailability()) {
            conditions.add("a.requiresAvailability = 1");
        }

        if (conditions.size() != 0) {
            customQuery.append(" a WHERE ");
            for (int i = 0; i < conditions.size(); i++) {
                customQuery.append(conditions.get(i));
                if (i != conditions.size() - 1) {
                    customQuery.append(" AND ");
                }
            }
        }


        logger.debug(customQuery);

        List<Asset> queriedAssetsList = assetService.executeCustomQuery(customQuery.toString());

        this.queriedAssetsList = queriedAssetsList;

        RiskEvaluationReportBuilder f = new RiskEvaluationReportBuilder();
        f.exportAssetsToDocxFile(queriedAssetsList, getLoggedInAuthority(), "D:/filema.docx");


        model.addAttribute("queriedAssetsList", queriedAssetsList);

        return "redirect:/riskmanager/query";

    }


    @ModelAttribute("existingPersons")
    public List<Person> getExistingPersons() {
        return personService.getAll();

    }

    @ModelAttribute("existingOrganisations")
    public List<Organisation> getExistingOrganisations() {
        return organisationService.getAll();

    }

    @ModelAttribute("queriedAssetsList")
    public List<Asset> getQueriedAssetsList() {
        return queriedAssetsList;
    }

    @ModelAttribute
    AssetQuery getAssetQuery() {
        return assetQuery;
    }

    @InitBinder
    public void initPersonPropertyEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Person.class, new PersonPropertyEditor(this.personService));
    }

    @InitBinder
    void initOrganisationPropertyBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Organisation.class, new OrganisationPropertyEditor(this.organisationService));
    }


    @ModelAttribute("loggedInAuthority")
    public Person getLoggedInAuthority() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String authorityLogin = auth.getName();
        return personService.getPersonByLogin(authorityLogin);
    }


}
