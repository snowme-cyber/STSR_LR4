package com.hibernate.xmlbased.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.xmlbased.config.SessionConfig;
import com.hibernate.xmlbased.model.Department;
import com.hibernate.xmlbased.model.Developer;

public class DepartmentDAO {

    private SessionConfig sc;

    public DepartmentDAO() {
        sc = SessionConfig.getInstanceOfSeccionFactory();
    }

	//Task 
	public Department findDepartmentByLetterInID(String letter) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Department department = session.createQuery("FROM Department d WHERE d.departmentId LIKE :letter", Department.class)
				.setParameter("letter", letter + "%")
				.setMaxResults(1)
				.uniqueResult();
		transaction.commit();
		session.close();
		return department;
	}



    public List<Department> getDepartments() {
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Department> deps = session.createQuery("FROM Department", Department.class).getResultList();
        transaction.commit();
        session.close();
        return deps;
    }

    // этот кусок кода решает проблему n+1
    public Set<Department> getDepartmentWithWorkers() {
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Set<Department> departments = new HashSet<>(session.createQuery("FROM Department d LEFT JOIN FETCH d.developers", Department.class).getResultList());
        transaction.commit();
        session.close();
        return departments;
    }

    public List<Developer> getDevelopersByDepartment(Department department) {
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Department dep = session.get(Department.class, department.getDepartmentId());
        Hibernate.initialize(dep.getDevelopers());
        List<Developer> devs = dep.getDevelopers();
        transaction.commit();
        session.close();
        return devs;
    }
	//Task
    public Department findDepartmentByID(String departmentID) {
        Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Department department = session.get(Department.class, departmentID);
		transaction.commit();
		session.close();
		return department;
    }
    // ЗАДАНИЕ
    public Department addDepartament(Department dep) {
      Session session = sc.getSessionFactory().getCurrentSession();
	  Transaction transaction = session.beginTransaction();
	  session.persist(dep);
	  transaction.commit();
	  session.close();
	  return dep;
    }
	// ЗАДАНИЕ
    public void deleteDepartment(String ID) {
        Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Department department = session.get(Department.class, ID);
		List<Developer> devs = department.getDevelopers();
        for (Developer dev : devs) {
            session.remove(dev);
        }
		session.remove(department);
		transaction.commit();
		session.close();
    }
}
