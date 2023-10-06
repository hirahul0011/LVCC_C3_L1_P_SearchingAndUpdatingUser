package com.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.user.entity.User;

@Repository
@Transactional
@EnableTransactionManagement
public class ParametersDAO {
	
	@Autowired	
	private HibernateTransactionManager hbnTransactionManager;
    
    public HibernateTransactionManager getHbnTransactionManager() {
		return hbnTransactionManager;
	}

	public void setHbnTransactionManager(HibernateTransactionManager hbnTransactionManager) {
		this.hbnTransactionManager = hbnTransactionManager;
	}
	
	public Session getSession() {
	    try {
	        return hbnTransactionManager.getSessionFactory().getCurrentSession();
	    } catch (Exception e) {
	        return hbnTransactionManager.getSessionFactory().openSession();
	    }
	}
	
	@SuppressWarnings("deprecation")
	public List<User> getTheUserDetails(String userId){ 
//		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
//		Query query= sessionFactory.getCurrentSession().createQuery("select u from User u where u.userId= :userId");
		Query<User> query= getSession().createQuery("select u from User u where u.userId= :userId",User.class);
        query.setParameter("userId", userId);
        return query.list();		
	}
	
	@SuppressWarnings("deprecation")
	public List<User> getAllUsersDetails(){
//		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
//		Query query= sessionFactory.getCurrentSession().createQuery("from User");
		Query query= getSession().createQuery("from User");
        return query.list();		
	}
	
	public void changeUserDetails(String userId,
			String userPassword, 
			String userFirstName,
			String userLastName,
			int userAge,
			String userGender){
		SessionFactory sessionFactory=hbnTransactionManager.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();	
		
		User u=session.get(User.class, userId);		
		
		u.setPassword(userPassword);
		u.setFirstName(userFirstName);
		u.setLastName(userLastName);
		u.setAge(userAge);
		u.setGender(userGender);
		
		session.persist(u);
		
		transaction.commit();		
		session.close();
	}

}
