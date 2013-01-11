package org.riskassess.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.riskassess.domain.basic.AssetType;
import org.riskassess.domain.basic.MediaType;
import org.riskassess.domain.complex.RiskDetail;
import org.riskassess.domain.complex.ScopeObject;
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

    public List<RiskDetail> getAllRiskDetails() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RiskDetail ");
        return query.list();
    }

    public List<ScopeObject> getAllScopeObjects() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ScopeObject ");
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

    public RiskDetail getRiskDetail(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        RiskDetail riskDetail = (RiskDetail) session.get(RiskDetail.class, id);
        return riskDetail;
    }

    public ScopeObject getScopeObject(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        ScopeObject scopeObject = (ScopeObject) session.get(ScopeObject.class, id);
        return scopeObject;
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

    public void addRiskDetail(RiskDetail riskDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(riskDetail);
    }



    public void addScopeObject(ScopeObject scopeObject) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(scopeObject);
    }
    /*/edit existing/*/

    public void editAssetType(AssetType assetType) {
        Session session = sessionFactory.getCurrentSession();

        AssetType storedAssetType = (AssetType) session.get(AssetType.class, assetType.getId());

        storedAssetType.setAssetTypeName(assetType.getAssetTypeName());
        /*storedAssetType.setRequiresAvail(assetType.getRequiresAvail());
        storedAssetType.setRequiresConf(assetType.getRequiresConf());
        storedAssetType.setRequiresIntegr(assetType.getRequiresIntegr());*/
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

    public void editRiskDetail(RiskDetail riskDetail) {
        Session session = sessionFactory.getCurrentSession();
        RiskDetail storedRiskDetail = (RiskDetail) session.get(RiskDetail.class, riskDetail.getId());

        /*storedRiskDetail.setAposterioryProtectionMeasures(riskDetail.getAposterioryProtectionMeasures());
        storedRiskDetail.setAprioryProtectionMeasures(riskDetail.getAprioryProtectionMeasures());

        storedRiskDetail.setOtherDataForSTP(riskDetail.getOtherDataForSTP());
        storedRiskDetail.setOtherDataForSVR(riskDetail.getOtherDataForSVR());*/
        storedRiskDetail.setPlannedMeasures(riskDetail.getPlannedMeasures());

        storedRiskDetail.setStpValue(riskDetail.getStpValue());
        storedRiskDetail.setSvrValue(riskDetail.getSvrValue());

        storedRiskDetail.setTargetProperty(riskDetail.getTargetProperty());

        /*storedRiskDetail.setThreatClass(riskDetail.getThreatClass());*/
        storedRiskDetail.setThreatImplementation(riskDetail.getThreatImplementation());
        storedRiskDetail.setThreatSource(riskDetail.getThreatSource());

        session.saveOrUpdate(storedRiskDetail);

    }

    public void editScopeObject(ScopeObject scopeObject) {
        Session session = sessionFactory.getCurrentSession();
        ScopeObject storedScopeObject = (ScopeObject) session.get(ScopeObject.class, scopeObject.getId());

        storedScopeObject.setAssetType(scopeObject.getAssetType());
        storedScopeObject.setMediaType(scopeObject.getMediaType());
        storedScopeObject.setRiskDetails(scopeObject.getRiskDetails());

        session.saveOrUpdate(storedScopeObject);

    }


    /*//delete existing*/

    public void deleteAssetType(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        AssetType assetType = (AssetType) session.get(AssetType.class, id);
        session.delete(assetType);
    }

    public void deleteMediaType(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        MediaType mediaType = (MediaType) session.get(MediaType.class, id);
        session.delete(mediaType);
    }

    public void deleteRiskDetail(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        RiskDetail riskDetail = (RiskDetail) session.get(RiskDetail.class, id);
        session.delete(riskDetail);
    }

    public void deleteScopeObject(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        ScopeObject scopeObject = (ScopeObject) session.get(ScopeObject.class, id);
        session.delete(scopeObject);
    }


    /*special*/

    public ScopeObject getScopeObjectByAssetAndMedia(Integer assetTypeID, Integer mediaTypeID) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ScopeObject as scope WHERE scope.assetType.id =:param1 AND scope.mediaType.id = :param2");
        query.setParameter("param1", assetTypeID);
        query.setParameter("param2", mediaTypeID);

        List<ScopeObject> result = query.list();

        if (result == null || result.size() == 0) {
            return null;
        } else if (result.size() > 1) {

            throw new Error("Not unique scope object!!!!!!");
        } else {
            return result.get(0);
        }

    }

    public List<RiskDetail> getRiskDetailsByAssetAndMedia(Integer assetTypeID, Integer mediaTypeID) {

        ScopeObject scopeObject = getScopeObjectByAssetAndMedia(assetTypeID, mediaTypeID);
        return scopeObject == null ? null : scopeObject.getRiskDetails();

    }
}
