package org.riskmanager.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.riskmanager.domain.Asset;
import org.riskmanager.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.10.12
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
@Service("assetService")
@Transactional
public class AssetService {

    Logger logger = Logger.getLogger("service");
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public List<Asset> getAll() {

        logger.debug("request to list all assets");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Asset");
        return query.list();
    }

    public List<Asset> getAllByPersonOrganisation(Person loggedInPerson) {

        logger.debug("listing all assets of user's organisation");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Asset a where a.personOwner.organisation.id = "
                + loggedInPerson.getOrganisation().getId());
        return query.list();
    }

    public Asset get(Integer id) {

        logger.debug("request to get asset id: " + id);
        Session session = sessionFactory.getCurrentSession();
        Asset asset = (Asset) session.get(Asset.class, id);
        return asset;
    }

    public void add(Asset asset) {

        logger.debug("request to add asset ");
        Session session = sessionFactory.getCurrentSession();
        session.save(asset);
    }


    public void delete(Integer id) {
        logger.debug("Deleting existing asset");
        Session session = sessionFactory.getCurrentSession();
        Asset asset = (Asset) session.get(Asset.class, id);
        session.delete(asset);
    }

    /**
     * Edits an existing Asset
     */
    public void edit(Asset asset) {
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


    public List<Asset> executeCustomQuery(String customQueryString) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(customQueryString);
        return query.list();
    }

}
