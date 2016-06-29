package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.ExperienceIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.ExperienceCandidat;

public class ExperienceIdaoImp implements ExperienceIdao {

	
	//*****************************************************************************************
	 
		SessionFactory sessionFactory;
	
	//***************************************************************************************** 
		
		
	@Override
	public void saveExperienceCandidat(ExperienceCandidat experienceCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(experienceCandidat);		
		trans.commit();
		
	}

	@Override
	public void updateExperienceCandidat(ExperienceCandidat experienceCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(experienceCandidat);		
		trans.commit();
		
	}

	@Override
	public void deleteExperienceCandidat(ExperienceCandidat experienceCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(experienceCandidat);		
		trans.commit();
		
	}

	@Override
	public List<ExperienceCandidat> getAllExperienceCandidat() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		List expcandidats = null;
		Query query =session.createQuery("from" + ExperienceCandidat.class);
		expcandidats = query.list();
		trans.commit();
        return expcandidats;
	}
	
	@Override
	public List<ExperienceCandidat> getAllExperienceCandidatById(int idcandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from ExperienceCandidat where candidat = :idcandidat");
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
