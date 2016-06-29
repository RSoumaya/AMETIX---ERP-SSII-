package com.resotrekk.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;

import com.resotrekk.dao.AdminIdao;
import com.resotrekk.model.Administrateur;
import com.resotrekk.model.Candidat;

public class AdminIdaoImp implements AdminIdao{

	
	//*****************************************************************************************
	 
		JdbcTemplate jt;
		SessionFactory sessionFactory;
		
	//***************************************************************************************** 
		
		
	@Override
	public List<Integer> getNumberDashboard() {
		
		List <Integer> list = new ArrayList<Integer>();;
		
		
		int countcandidat = jt
				.queryForInt("select count(*)from Candidat");
	
		
		int countcv = jt
				.queryForInt("select count(*)from Candidat where cv_candidat !='' ");
		
		
		int countcandidature = jt
				.queryForInt("select count(*)from Candidatures");
		
		int countoffre = jt
				.queryForInt("select count(*)from Offre");
		
		int countmessages = jt
				.queryForInt("select count(*)from contact");
		
		int countfeedback = jt
				.queryForInt("select count(*)from feedback");
		
		
		list.add(countcandidat);
		list.add(countcv);
		list.add(countcandidature);
		list.add(countoffre);
		list.add(countmessages);
		list.add(countfeedback);
		
		return list;
	}

	@Override
	public void saveAdministrateur(Administrateur admin) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(admin);
		trans.commit();	
	}
	
	@Override
	public int authentification(String mail, String password) {

		int count = 0;
		
		String sql = "select count(*) from Administrateur WHERE email_admin = ? and mot_de_passe = ?";
		
		count = jt.queryForObject(
                sql, new Object[] { mail, password }, Integer.class);
		return count;
		
	}
	
	@Override
	public int existAdmin(String mail) {
		
		int count = 0;
		
		String sql = "select count(*) from Administrateur WHERE email_admin = ? ";
		
		count = jt.queryForObject(
                sql, new Object[] { mail }, Integer.class);
		return count;
	}
	
	@Override
	public Administrateur findAdminByMail(String mail) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Administrateur where emailAdmin = :mail");
				query.setString("mail", mail);
		List results = query.list();
		trans.commit();
		return  (Administrateur) (results.isEmpty()? null:results.get(0));
	}

	@Override
	public List<Administrateur> getAllAdmin() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Administrateur");
		List results = query.list();
		trans.commit();
		return  results;
	}
	
	//*****************************************************************************************
	
	public JdbcTemplate getJt() {
		return jt;
	}
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//*****************************************************************************************
}
