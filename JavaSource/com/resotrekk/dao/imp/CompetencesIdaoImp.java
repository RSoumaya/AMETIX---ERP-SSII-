package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.CompetencesIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Competences;
import com.resotrekk.model.ExperienceCandidat;
import com.resotrekk.model.LangueCandidat;

public class CompetencesIdaoImp implements CompetencesIdao {

	//*****************************************************************************************
	 
	SessionFactory sessionFactory;
	
	//***************************************************************************************** 
	
	
	@Override
	public void saveCompetence(Competences competences) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(competences);
		trans.commit();
	}

	@Override
	public void deleteCompetence(Competences competences) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(competences);
		trans.commit();	
	}
	
	@Override
	public List<Competences> getAllCompetences() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Competences");
		List results = query.list();
		trans.commit();
		return  results;
	}

	@Override
	public Competences findCompetenceByLibelle(int idcomp) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Competences where idcompetences = :idcomp");
		query.setInteger("idcomp", idcomp);
		List results = query.list();
		trans.commit();
		return  (Competences) (results.isEmpty()? null:results.get(0));
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
