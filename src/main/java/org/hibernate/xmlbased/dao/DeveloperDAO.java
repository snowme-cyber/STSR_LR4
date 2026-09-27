package org.hibernate.xmlbased.dao;
import java.util.List;

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

    public void addDeveloper(Developer developer) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(developer);
            transaction.commit();
        } catch (Exception e) {
            // Обязательно откатываем транзакцию при ошибке (дубликате)
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        } finally {
            session.close();
        }
    }

    public Developer getDeveloperById(Integer id) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        transaction.commit();
        session.close();
        return developer;
    }

    public List<Developer> getDevelopers() {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer", Developer.class).list();
        transaction.commit();
        session.close();
        return developers;
    }

    public Developer updateDeveloper(Integer id, Integer experience) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);

        if (developer != null) {
            developer.setExperience(experience);
        } else {
            System.out.println("Ошибка обновления: Разработчик с ID=" + id + " не найден.");
        }

        transaction.commit();
        session.close();
        return developer;
    }

    public void removeDeveloper(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);

        if (developer != null) {
            session.remove(developer);
        } else {
            System.out.println("Ошибка удаления: Разработчик с ID=" + id + " не найден.");
        }

        transaction.commit();
        session.close();
    }
}
