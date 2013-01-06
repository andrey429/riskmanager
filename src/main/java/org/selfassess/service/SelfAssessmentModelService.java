package org.selfassess.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.selfassess.domain.SelfAssessmentModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 03.01.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Service("selfAssessmentModelService")
@Transactional
public class SelfAssessmentModelService {

    Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SelfAssessmentModel get(Integer id) {

        logger.debug("request to get SelfAssessmentModel id: " + id);
        Session session = sessionFactory.getCurrentSession();
        SelfAssessmentModel saModel = (SelfAssessmentModel) session.get(SelfAssessmentModel.class, id);
        return saModel;
    }

    public List<SelfAssessmentModel> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM SelfAssessmentModel ");
        return query.list();
    }

    /*public void add(SelfAssessmentModel saModel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(saModel);
    }*/

    public void add(SelfAssessmentModel saModel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(saModel);
    }

    public void edit(SelfAssessmentModel currentModel) {
        Session session = sessionFactory.getCurrentSession();
        SelfAssessmentModel storedModel = (SelfAssessmentModel) session.get(SelfAssessmentModel.class, currentModel.getId());

        /*storedModel.setEv1Model(currentModel.getEv1Model());todo that causes NULL-value  bug*/
        storedModel.setAuditors(currentModel.getAuditors());
        storedModel.setCreator(currentModel.getCreator());
        storedModel.setDescription(currentModel.getDescription());
        storedModel.setSelfAssessmentName(currentModel.getSelfAssessmentName());

        session.saveOrUpdate(storedModel);

    }


}
