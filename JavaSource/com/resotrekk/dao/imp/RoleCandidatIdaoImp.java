package com.resotrekk.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.resotrekk.dao.RoleCandidatIdao;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.RoleCandidat;

public class RoleCandidatIdaoImp  implements RoleCandidatIdao{

	
	//*****************************************************************************************
	 
		SessionFactory sessionFactory;
		
    //***************************************************************************************** 
		
	@Override
	public void saveRoleCandidat(RoleCandidat roleCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(roleCandidat);
		trans.commit();
	}

	@Override
	public void updateRoleCandidat(RoleCandidat roleCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(roleCandidat);
		trans.commit();
	}

	@Override
	public void deleteRoleCandidat(RoleCandidat roleCandidat) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(roleCandidat);
		trans.commit();
	}

	@Override
	public RoleCandidat findRoleCandidatById(Serializable id) {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		RoleCandidat rolecandidat = null; 
		rolecandidat = (RoleCandidat) session.load(RoleCandidat.class, id);
		trans.commit();
		return rolecandidat;
	}

	@Override
	public List<RoleCandidat> getAllRoleCandidat() {
		
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		List rolecandidat = null;
		Query query =session.createQuery("from" + RoleCandidat.class);
		rolecandidat = query.list();
		trans.commit();
        return rolecandidat;
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
