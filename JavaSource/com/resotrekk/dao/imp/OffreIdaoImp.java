package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.OffreIdao;
import com.resotrekk.model.CompetencesCandidat;
import com.resotrekk.model.LangueCandidat;
import com.resotrekk.model.Offre;

public class OffreIdaoImp implements OffreIdao{

	//*****************************************************************************************
	 
		SessionFactory sessionFactory;

	//***************************************************************************************** 
		
	@Override
	public void saveOffre(Offre offre) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(offre);		
		trans.commit();
		
	}

	@Override
	public void updateOffre(Offre offre) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(offre);		
		trans.commit();
		
	}

	@Override
	public void deleteOffre(Offre offre) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(offre);		
		trans.commit();	
	}

	@Override
	public List<Offre> getAllOffre() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Offre");
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
