package org.selfassess.service;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.selfassess.domain.SelfAssessmentModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    public void add(SelfAssessmentModel saModel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(saModel);
    }

}
