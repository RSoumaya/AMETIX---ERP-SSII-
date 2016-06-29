package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.CompetencesCandidatIdao;
import com.resotrekk.model.CompetencesCandidat;
import com.resotrekk.model.ExperienceCandidat;

public class CompetencesCandidatIdaoImp implements CompetencesCandidatIdao{

	//*****************************************************************************************
	 
	SessionFactory sessionFactory;
			
	//***************************************************************************************** 
			
	@Override
	public void saveCompetencesCandidat(
			CompetencesCandidat competencesCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(competencesCandidat);		
		trans.commit();
	}


	@Override
	public void deleteCompetencesCandidat(
			CompetencesCandidat competencesCandidat) {
	
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(competencesCandidat);		
		trans.commit();
		
	}
	@Override
	public List<CompetencesCandidat> getAllCompetencesCandidatById(
			int idcandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from CompetencesCandidat where candidat = :idcandidat");
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
