package org.hibernate.xmlbased.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.xmlbased.model.Developer;


public class DeveloperDAO {
    private final SessionFactory sessionFactory;
    private Session session;

    public DeveloperDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void AddDevelopers(Developer developer){
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.persist(developer);

        transaction.commit();
        session.close();
    }
    public  Developer getDeveloperById(Integer id){
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        transaction.commit();
        session.close();


        return developer;
    }

}
