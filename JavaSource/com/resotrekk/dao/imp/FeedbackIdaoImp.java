package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.FeedbackIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Feedback;

public class FeedbackIdaoImp implements FeedbackIdao{

	//*****************************************************************************************
	 
		SessionFactory sessionFactory;
		
	//***************************************************************************************** 
		
	@Override
	public void saveFeedback(Feedback feedback) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(feedback);
		trans.commit();
		
	}

	@Override
	public List<Feedback> getAllFeedback() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Feedback");
		List results = query.list();
		trans.commit();
		return  results;
	}

	//*****************************************************************************************

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//*****************************************************************************************
}
