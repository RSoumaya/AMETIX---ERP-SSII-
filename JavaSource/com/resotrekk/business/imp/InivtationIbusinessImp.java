package com.resotrekk.business.imp;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.InvitationIbusiness;
import com.resotrekk.dao.InvitationIdao;
import com.resotrekk.model.Invitation;

public class InivtationIbusinessImp implements InvitationIbusiness{

	
	//*************************************************************************
	
	InvitationIdao invitationDao;
	
	//*************************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterInvitation(Invitation invitation) {
		
		try{
			invitationDao.saveInvitation(invitation);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Invitation> consulterInvitation() {
		
		List<Invitation> listinvitation = null;
		try{
			listinvitation = invitationDao.getAllInvitation();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return listinvitation;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Invitation consulterInvitation(String title) {
		
		Invitation invitation = null;
		try{
			invitation = invitationDao.getInvitation(title);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return invitation;
	}

	//*************************************************************************
	
	public InvitationIdao getInvitationDao() {
		return invitationDao;
	}
	public void setInvitationDao(InvitationIdao invitationDao) {
		this.invitationDao = invitationDao;
	}

	
	//*************************************************************************

}
