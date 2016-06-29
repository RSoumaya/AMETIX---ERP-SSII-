package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.ContactIbusiness;
import com.resotrekk.dao.ContactIdao;
import com.resotrekk.model.Contact;

public class ContactIbusinessImp implements ContactIbusiness{

	//****************************************************************
	
	ContactIdao contactDao;
	
	//****************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterContact(Contact contact) {
		
		try{
			contactDao.saveContact(contact);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Contact> retournerTousContact() {
		
		List<Contact> list = null;
		try{
			list = contactDao.getAllContact();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	
	//****************************************************************
	
	public ContactIdao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactIdao contactDao) {
		this.contactDao = contactDao;
	}

	
	//****************************************************************
}
