package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.resotrekk.dao.HistoriqueIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Historique;

public class HistoriqueIdaoImp implements HistoriqueIdao{

	
	//*****************************************************************************************
	
		SessionFactory sessionFactory;
		
	//***************************************************************************************** 
		
	@Override
	public void saveHistorique(Historique historique) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(historique);
		trans.commit();
	}

	@Override
	public List<Historique> getHistorique() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List list = session.createCriteria(Historique.class)
			    .addOrder( Order.asc("idhistorique") )
			    .list();
		trans.commit();
		return  list;
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
