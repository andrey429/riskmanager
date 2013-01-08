package org.riskassess.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.riskassess.domain.basic.AssetType;
import org.riskassess.domain.basic.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */

@Service("hibernateService")
@Transactional
public class HibernateService {
    Logger logger = Logger.getLogger("controller");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /*//get all*/
    public List<AssetType> getAllAssetTypes() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM AssetType ");
        return query.list();
    }

    public List<MediaType> getAllMediaTypes() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM MediaType ");
        return query.list();
    }

    /*//get by id*/

    public AssetType getAssetType(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        AssetType assetType = (AssetType) session.get(AssetType.class, id);
        return assetType;
    }

    public MediaType getMediaType(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        MediaType mediaType = (MediaType) session.get(MediaType.class, id);
        return mediaType;
    }
    /*//create new*/

    public void addAssetType(AssetType assetType) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(assetType);
    }
    public void addMediaType(MediaType mediaType) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(mediaType);
    }

    /*/edit existing/*/

    public void editAssetType(AssetType assetType) {
        Session session = sessionFactory.getCurrentSession();

        AssetType storedAssetType = (AssetType) session.get(AssetType.class, assetType.getId());

        storedAssetType.setAssetTypeName(assetType.getAssetTypeName());
        storedAssetType.setRequiresAvail(assetType.getRequiresAvail());
        storedAssetType.setRequiresConf(assetType.getRequiresConf());
        storedAssetType.setRequiresIntegr(assetType.getRequiresIntegr());
        storedAssetType.setDescription(assetType.getDescription());

        session.saveOrUpdate(storedAssetType);

    }
    public void editMediaType(MediaType mediaType) {
        Session session = sessionFactory.getCurrentSession();
        MediaType storedMediaType = (MediaType) session.get(MediaType.class, mediaType.getId());

        storedMediaType.setMediaTypeName(mediaType.getMediaTypeName());
        storedMediaType.setDescription(mediaType.getDescription());

        session.saveOrUpdate(storedMediaType);

    }

    /*//delete existing*/

    public void deleteAssetType(Integer id){
        Session session = sessionFactory.getCurrentSession();
        AssetType assetType = (AssetType) session.get(AssetType.class, id);
        session.delete(assetType);
    }
    public void deleteMediaType(Integer id){
        Session session = sessionFactory.getCurrentSession();
        MediaType mediaType = (MediaType) session.get(MediaType.class, id);
        session.delete(mediaType);
    }

}
