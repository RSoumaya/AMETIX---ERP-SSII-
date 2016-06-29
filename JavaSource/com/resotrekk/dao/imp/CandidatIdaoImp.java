package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import com.resotrekk.dao.CandidatIdao;
import com.resotrekk.model.Candidat;

public class CandidatIdaoImp implements CandidatIdao {
	

	//*****************************************************************************************
	
	JdbcTemplate jt;
	SessionFactory sessionFactory;
	
	//***************************************************************************************** 
	
	@Override
	public void saveCandidat(Candidat candidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(candidat);
		trans.commit();
	}

	@Override
	public void updateCandidat(Candidat candidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(candidat);
		trans.commit();
	}

	@Override
	public void deleteCandidat(Candidat candidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(candidat);
		trans.commit();
	}

	@Override
	public Candidat findCandidatById(int id) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Candidat candidat = null; 
		candidat = (Candidat) session.load(Candidat.class, id);
		trans.commit();
		return candidat;
	}
	
	@Override
	public Candidat findCandidatByIdWithoutTransaction(int id) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Candidat candidat = null; 
		candidat = (Candidat) session.load(Candidat.class, id);
		return candidat;
	}

	@Override
	public List<Candidat> getAllCandidat() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		List list = session.createCriteria(Candidat.class)
			    .addOrder( Order.asc("nomCandidat") )
			    .list();
		trans.commit();
		return  list;
	}

	
	@Override
	public Candidat findCandidatByEmail(String email) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Candidat where emailCandidat = :emailCandidat");
		query.setString("emailCandidat", email);
		List results = query.list();
		trans.commit();
		return  (Candidat) (results.isEmpty()? null:results.get(0));
		}
	
	
	@Override
	public List<Candidat> getLastTenCandidat() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		List list = session.createCriteria(Candidat.class)
			    .addOrder( Order.asc("idCandidat") )
			    .setMaxResults(10)
			    .list();
		trans.commit();
		return  list;
		
	}
	
	@Override
	public int existCandidat(String mail) {
		
		int count = 0; 
		
		String sql = "select count(*) from Candidat WHERE email_candidat = ? ";
		
		count = jt.queryForObject(
                sql, new Object[] { mail }, Integer.class);
		return count;
		
	}
	
	@Override
	public int findCandidatByNomAndPrenom(String nom, String prenom) {
		
		
		String sql = "select count(*) from Candidat WHERE nom_candidat = ? and prenom_candidat = ? ";
		
		int count = jt.queryForObject(
                sql, new Object[] { nom, prenom }, Integer.class);
		return count;
		
	}
	//****************************************************************************************
	
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
	//****************************************************************************************

	

	

	
}
