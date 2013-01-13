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
	
	@Resource(name="personService")
	private PersonService personService;
    @Resource(name="organisationService")
    OrganisationService organisationService;
	
	/**
	 * Handles and retrieves all persons and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPersons(Model model) {

    	logger.debug("Received request to show all persons");
    	
    	// Retrieve all persons by delegating the call to PersonService
    	List<Person> persons = personService.getAll();
    	
    	// Attach persons to the Model
    	model.addAttribute("persons", persons);
    	
    	// This will resolve to /WEB-INF/jsp/persons_list_page.jsp
    	return "person_views/persons_list_page";
	}
    
    /**
     * Retrieves the add page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Create new Person and add to model
    	// This is the formBackingOBject
    	model.addAttribute("personAttribute", new Person());

    	// This will resolve to /WEB-INF/jsp/person_addpage.jsp
    	return "person_views/person_addpage";
	}
 
    /**
     * Adds a new person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("personAttribute") Person person) {
		logger.debug("Received request to add new person");
		
    	// The "personAttribute" model has been passed to the controller from the JSP
    	// We use the name "personAttribute" because the JSP uses that name
		
		// Call PersonService to do the actual adding
		personService.add(person);

    	// This will resolve to /WEB-INF/jsp/person_addedpage.jsp
 		return "redirect:/riskmanager/persons/";
	}
    
    /**
     * Deletes an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id, 
    										Model model) {
   
		logger.debug("Received request to delete existing person");
		
		// Call PersonService to do the actual deleting
		personService.delete(id);
		
		// Add id reference to Model
		model.addAttribute("id", id);
    	
    	// This will resolve to /WEB-INF/jsp/person_deletedpage.jsp
        return "redirect:/riskmanager/persons/";
	}
    
    /**
     * Retrieves the edit page
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Integer id,  
    										Model model) {
    	logger.debug("Received request to show edit page");
    
    	// Retrieve existing Person and add to model
    	// This is the formBackingOBject
    	model.addAttribute("personAttribute", personService.get(id));
    	

    	return "person_views/person_addpage";
	}
    
    /**
     * Edits an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     * 
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("personAttribute") Person person, 
    										   @RequestParam(value="id", required=true) Integer id, 
    												Model model) {
    	logger.debug("Received request to saveOrUpdate person");
    
    	// The "personAttribute" model has been passed to the controller from the JSP
    	// We use the name "personAttribute" because the JSP uses that name
    	
    	// We manually assign the id because we disabled it in the JSP page
    	// When a field is disabled it will not be included in the ModelAttribute
    	person.setId(id);
    	
    	// Delegate to PersonService for editing
    	personService.edit(person);
    	


		
    	// This will resolve to /WEB-INF/jsp/person_editedpage.jsp
        return "redirect:/riskmanager/persons/";
	}

    @ModelAttribute("existingOrganisations")
    public List<Organisation> getExistingOrganisations(){
        return organisationService.getAll();
    }

    @InitBinder
    public void initOrganisationPropertyEditorBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Organisation.class,new OrganisationPropertyEditor(this.organisationService));

    }

    @InitBinder
    public void initPersonPasswordPropertyEditorBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(String.class, "passwordHash", new PersonPasswordPropertyEditor());
    }



}
