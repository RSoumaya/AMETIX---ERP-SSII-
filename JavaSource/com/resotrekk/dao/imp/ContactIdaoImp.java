package com.resotrekk.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.ContactIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Contact;

public class ContactIdaoImp implements ContactIdao{

	//*****************************************************************************************
	 
		SessionFactory sessionFactory;

	//***************************************************************************************** 
	
		@Override
		public void saveContact(Contact contact) {
		
			Session session = getSessionFactory().getCurrentSession();
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(contact);		
			trans.commit();		
	}

		@Override
		public List<Contact> getAllContact() {
			
			Session session = getSessionFactory().getCurrentSession();
			Transaction trans = session.beginTransaction();
			Query query =session.createQuery("from Contact");
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
