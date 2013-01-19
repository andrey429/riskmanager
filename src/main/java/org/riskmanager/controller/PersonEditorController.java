package org.riskmanager.controller;

import org.apache.log4j.Logger;
import org.riskmanager.converters.OrganisationPropertyEditor;
import org.riskmanager.converters.PersonPasswordPropertyEditor;
import org.riskmanager.domain.Organisation;
import org.riskmanager.domain.Person;
import org.riskmanager.service.OrganisationService;
import org.riskmanager.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/persons")
public class PersonEditorController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "personService")
    private PersonService personService;
    @Resource(name = "organisationService")
    OrganisationService organisationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPersons(Model model) {
        List<Person> persons = personService.getAll();
        model.addAttribute("persons", persons);
        return "person_views/persons_list_page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        model.addAttribute("personAttribute", new Person());
        return "person_views/person_addpage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("personAttribute") Person person) {

        personService.add(person);
        return "redirect:/riskmanager/persons/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Integer id,
                         Model model) {

        personService.delete(id);
        model.addAttribute("id", id);
        return "redirect:/riskmanager/persons/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) Integer id,
                          Model model) {

        model.addAttribute("personAttribute", personService.get(id));
        return "person_views/person_addpage";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("personAttribute") Person person,
                           @RequestParam(value = "id", required = true) Integer id,
                           Model model) {

        person.setId(id);
        personService.edit(person);
        return "redirect:/riskmanager/persons/";
    }

    @ModelAttribute("existingOrganisations")
    public List<Organisation> getExistingOrganisations() {
        return organisationService.getAll();
    }

    @InitBinder
    public void initOrganisationPropertyEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Organisation.class, new OrganisationPropertyEditor(this.organisationService));

    }

    @InitBinder
    public void initPersonPasswordPropertyEditorBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "passwordHash", new PersonPasswordPropertyEditor());
    }
}
