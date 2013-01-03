package org.selfassess.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.selfassess.domain.EV1Model;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Service("ev1ModelService")
@Transactional
public class EV1ModelService{




    Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public List<EV1Model> getAllEV1Evaluations() {

        logger.debug("request to list all EV1Model's");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM EV1Model");
        return query.list();
    }


    public EV1Model get(Integer id) {

        logger.debug("request to get EV1Model id: " + id);
        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        return ev1Model;
    }

    public void add(EV1Model ev1Model) {

        logger.debug("request to add ev1FBO ");
        Session session = sessionFactory.getCurrentSession();




        session.save(ev1Model);
    }


    public void delete(Integer id) {
        logger.debug("Deleting existing ev1FBO");
        Session session = sessionFactory.getCurrentSession();
        EV1Model ev1Model = (EV1Model) session.get(EV1Model.class, id);
        session.delete(ev1Model);
    }




}
