package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.LangueIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.ExperienceCandidat;
import com.resotrekk.model.LangueCandidat;

public class LangueIdaoImp implements LangueIdao{

	
	//*****************************************************************************************
	 
		SessionFactory sessionFactory;
		
	//***************************************************************************************** 
			
			
	@Override
	public void saveLangueCandidat(LangueCandidat langueCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(langueCandidat);		
		trans.commit();
		
	}

	@Override
	public void updateLangueCandidat(LangueCandidat langueCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(langueCandidat);		
		trans.commit();
		
	}

	@Override
	public void deleteLangueCandidat(LangueCandidat langueCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(langueCandidat);		
		trans.commit();
		
	}

	@Override
	public List<LangueCandidat> getAllLangueCandidat() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		List languecandidats = null;
		Query query = session.createQuery("from" + LangueCandidat.class);
		languecandidats = query.list();
		trans.commit();
        return languecandidats;
	}

	
	@Override
	public List<LangueCandidat> getAllLangueCandidatById(int idcandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from LangueCandidat where candidat = :idcandidat");
		query.setInteger("idcandidat", idcandidat);
		List results = query.list();
		trans.commit();
		return  results;
	}
	
	//****************************************************************************************
	
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		
		
	//****************************************************************************************
}
