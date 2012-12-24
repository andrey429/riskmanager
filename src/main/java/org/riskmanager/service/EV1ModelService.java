package org.riskmanager.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.riskmanager.domain.chapters.EV1Model;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("ev1ModelService")
@Transactional
public class EV1ModelService  {


    Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public List<EV1Model> getAllEV1Evaluations() {

        logger.debug("request to list all EV1Model's");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Asset");
        return query.list();
    }


    public EV1Model get(Integer id) {

        logger.debug("request to get EV1Model id: " + id);
        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        return ev1Model;
    }

    public void add(EV1Model ev1Model) {

        logger.debug("request to add ev1Model ");
        Session session = sessionFactory.getCurrentSession();
        logger.debug("ev1:");
        session.save(ev1Model);
    }


    public void delete(Integer id) {
        logger.debug("Deleting existing ev1Model");
        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        session.delete(ev1Model);
    }

    /**
     * Edits an existing Asset
     */
    /*public void edit(Asset asset) {
        logger.debug("Editing existing Asset");
        Session session = sessionFactory.getCurrentSession();
        Asset existingAsset = (Asset) session.get(Asset.class, asset.getId());

        existingAsset.setDescription(asset.getDescription());
        existingAsset.setId(asset.getId());
        existingAsset.setName(asset.getName());
        existingAsset.setRequiresAvailability(asset.getRequiresAvailability());
        existingAsset.setRequiresIntegrity(asset.getRequiresIntegrity());
        existingAsset.setRequiresConfidentiality(asset.getRequiresConfidentiality());
        existingAsset.setAssetLocation(asset.getAssetLocation());
        existingAsset.setBusinessProcessType(asset.getBusinessProcessType());
        existingAsset.setPersonOwner(asset.getPersonOwner());

        session.save(existingAsset);
    }
*/

    /*public List<EV1Model> executeCustomQuery(String customQueryString) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(customQueryString);
        return query.list();
    }*/


}
