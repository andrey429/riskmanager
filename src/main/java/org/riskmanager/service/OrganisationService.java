package org.riskmanager.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.riskmanager.domain.Organisation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 07.10.12
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
@Service("organisationService")
@Transactional
public class OrganisationService {

    protected static Logger logger =  Logger.getLogger("service");
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;



    public List<Organisation> getAll(){

        logger.debug("Getting all the organisations");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Organisation ");
        return query.list();
    }

    public Organisation get(Integer id){
        logger.debug("Getting organisation id: "+id.toString());
        Session session = sessionFactory.getCurrentSession();
        Organisation organisation = (Organisation) session.get(Organisation.class, id);
        return organisation;
    }


    public void add(Organisation organisation){

        logger.debug("Adding new organisation");
        Session session = sessionFactory.getCurrentSession();
        session.save(organisation);
    }


    public void delete(Integer id){

        logger.debug("Deleting organisation id: "+ id);
        Session session = sessionFactory.getCurrentSession();
        Organisation organisation = (Organisation) session.get(Organisation.class, id);
        session.delete(organisation);
    }


    public void edit(Organisation organisation){

        logger.debug("Editing existing organisation");

        Session session = sessionFactory.getCurrentSession();
        Organisation existingOrganisation = (Organisation) session.get(Organisation.class, organisation.getId());

        existingOrganisation.setOrganisationAddress(organisation.getOrganisationAddress());
        existingOrganisation.setOrganisationName(organisation.getOrganisationName());

        session.save(existingOrganisation);
    }


}
