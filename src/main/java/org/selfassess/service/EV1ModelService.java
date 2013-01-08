package org.selfassess.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.selfassess.domain.EV1Model;
import org.selfassess.domain.SelfAssessmentModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Service("ev1ModelService")
@Transactional
public class EV1ModelService {




    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public List<EV1Model> getAllEV1Evaluations() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM EV1Model");
        return query.list();
    }


    public EV1Model get(Integer id) {


        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        return ev1Model;
    }

    public void add(EV1Model ev1Model) {

        Session session = sessionFactory.getCurrentSession();
        session.save(ev1Model);
    }


    public void delete(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        session.delete(ev1Model);
    }


    public EV1Model getBySelfAssessmentID(Integer assessmentID) {
        Session session = sessionFactory.getCurrentSession();
        /*SelfAssessmentModel saModel = (SelfAssessmentModel) session.get(SelfAssessmentModel.class, assessmentID);*/
        return ((SelfAssessmentModel) session.get(SelfAssessmentModel.class, assessmentID)).getEv1Model();
        /*List<Integer> ev1List =
                session.createQuery("select a.ev1Model.id FROM SelfAssessmentModel AS a WHERE a.id=" + assessmentID).list();

        if (ev1List == null || ev1List.get(0) == null) {
            return null;

        } else {
            logger.debug("list of evs is : " + ev1List);
            return (EV1Model) session.get(EV1Model.class, ev1List.get(0));
        }*/


        /*List<EV1Model> result = session.createQuery("FROM EV1Model AS ev1, SelfAssessmentModel AS sa " +
                "WHERE ev1.id = sa.ev1Model.id AND sa.id = :assessmentID").setParameter("assessmentID", assessmentID).list();
        if (result == null) return null;
        else return result.get(0);*/


    }

/*
    public void saveOrUpdate(EV1Model ev1Model) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(ev1Model);
    }
*/

    public void edit(EV1Model currentModel) {
        Session session = sessionFactory.getCurrentSession();

        EV1Model storedInDB = (EV1Model) session.get(EV1Model.class, currentModel.getId());
        storedInDB.setmParamValues(currentModel.getmParamValues());
        storedInDB.setEv1value(currentModel.getEv1value());
        storedInDB.setmGroupValues(currentModel.getmGroupValues());

        session.saveOrUpdate(storedInDB);
    }


}
