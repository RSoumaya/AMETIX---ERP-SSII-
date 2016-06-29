package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;

import com.resotrekk.dao.CandidaturesIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Candidatures;

public class CandidaturesIdaoImp implements CandidaturesIdao{

	
	//*****************************************************************************************
	 
		SessionFactory sessionFactory;
		JdbcTemplate jt;
	//***************************************************************************************** 
		
		
	@Override
	public void saveCandidatures(Candidatures candidatures) {
	
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(candidatures);
		trans.commit();	
	}

	@Override
	public int getCandidatureAsOffreIDandCandidatId(int offre, int candidat) {
		
		int count = 0;
		
		String sql = "select count(*) from Candidatures WHERE offre = ? and candidat = ?";
		
		count = jt.queryForObject(
                sql, new Object[] { offre, candidat }, Integer.class);
		return count;
		
	}

	@Override
	public List<Candidatures> getCandidatures() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Candidatures");
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
	public JdbcTemplate getJt() {
		return jt;
	}
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	//*****************************************************************************************

	

	
}
