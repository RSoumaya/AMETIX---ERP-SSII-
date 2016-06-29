package com.resotrekk.dao.imp;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.InvitationIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Invitation;

public class InvitationIdaoImp implements InvitationIdao{

	
	//*****************************************************************************************
	 
	SessionFactory sessionFactory;
	
	//***************************************************************************************** 
			
	@Override
	public void saveInvitation(Invitation invitation) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(invitation);
		trans.commit();
	}

	
	@Override
	public Invitation getInvitation(String title) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Invitation where title = :title");
		query.setString("title", title);
		List results = query.list();
		trans.commit();
		return  (Invitation) (results.isEmpty()? null:results.get(0));
	}
	
	
	@Override
	public List<Invitation> getAllInvitation() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query =session.createQuery("from Invitation");
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
