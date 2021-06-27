package com.example.restApplications.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restApplications.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> findAll() {
		Session session=this.sessionFactory.openSession();
		List<User> userList=session.createQuery("select a from User a").getResultList();
		session.close();
		return userList;
	}

	public void saveOrUpdate(User user) {
		Session session=this.sessionFactory.openSession();
		session.saveOrUpdate(user);
		session.close();
	}

	public void deleteUser(Integer id) {
		Session session=this.sessionFactory.openSession();
		User user=session.get(User.class,new Integer(id));
		if(user!=null) {
			session.delete(user);
		}
	}

	public User getUserById(Integer id) {
		Session session=this.sessionFactory.openSession();
		User user=session.load(User.class, id);
		return user;
	}

	public List<User> getUserByIdORName(Integer id, String name) {
		Session session=this.sessionFactory.openSession();
		org.hibernate.query.Query query=session.createQuery("from User where id=:id and name=:name");
		query.setParameter("id", id);
		query.setParameter("name", name);
		List<User> userList=query.list();
		session.close();
		return userList;
	}
	
	//Named Query
	public List<User> getUserByName(String name) {
		Session session=sessionFactory.openSession();
		org.hibernate.query.Query query=session.getNamedQuery("getUserByName");
		query.setParameter("name", name);
		query.setCacheable(true);
		return query.getResultList();
	}
	
}
