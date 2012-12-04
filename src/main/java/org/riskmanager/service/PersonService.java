package org.riskmanager.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.riskmanager.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Persons
 * 
 */
@Service("personService")
@Transactional
public class PersonService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Person");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single person
	 */
	public Person get( Integer id ) {

        logger.debug("Getting Person id: "+id.toString());
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing person first
		Person person = (Person) session.get(Person.class, id);
		return person;
	}
	/**
	 * Adds a new person
	 */

	public void add(Person person) {

        logger.debug("Adding new person");
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Save
		session.save(person);
	}
	
	/**
	 * Deletes an existing person
	 * @param id the id of the existing person
	 */
	public void delete(Integer id) {

        logger.debug("Deleting existing person");
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing person first
		Person person = (Person) session.get(Person.class, id);
		// Delete 
		session.delete(person);
	}
	
	/**
	 * Edits an existing person
	 */
	public void edit(Person person) {

        logger.debug("Editing existing person");
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, person.getId());
		// Assign updated values to this person
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		existingPerson.setSecondName(person.getSecondName());
        existingPerson.setJobPosition(person.getJobPosition());
        existingPerson.setOrganisation(person.getOrganisation());
        existingPerson.setDepartment(person.getDepartment());
        existingPerson.setLogin(person.getLogin());
        existingPerson.setPasswordHash(person.getPasswordHash());
        existingPerson.setAccessLevel(person.getAccessLevel());

		// Save updates
		session.save(existingPerson);
	}

    public Person getPersonByLogin(String login){

        logger.debug("getting user by login: "+ login);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Person p where p.login LIKE \'"+login+"\'");
        return (Person) query.uniqueResult();
    }

}
