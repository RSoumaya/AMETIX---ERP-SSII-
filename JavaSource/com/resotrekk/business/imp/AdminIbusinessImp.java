package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.AdminIbusiness;
import com.resotrekk.dao.AdminIdao;
import com.resotrekk.model.Administrateur;

public class AdminIbusinessImp implements AdminIbusiness {

	
	//*****************************************************************
	
	AdminIdao adminDao;
	
	//*****************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterAdmin(Administrateur admin) {
		
		try{
			adminDao.saveAdministrateur(admin);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Integer> retournerNombres() {
		
		List<Integer>  list = null;
		try{
			
			list = adminDao.getNumberDashboard();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public int authentificationn(String mail, String password) {
		
		int count = 0;
		try{
			count = adminDao.authentification(mail, password);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return count;
	}

	@Transactional(readOnly = true)
	@Override
	public Administrateur retournerAdminMail(String mail) {
		
		Administrateur admin = null;
		try{
			admin = adminDao.findAdminByMail(mail);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return admin;
	}
	
	@Transactional(readOnly = true)
	@Override
	public int authentificationnAdmin(String mail) {
		
		int count = 0;
		try{
			count = adminDao.existAdmin(mail);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return count;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Administrateur> retournerAdministrateur() {
		
		List<Administrateur> list = null;
		try{
			list = adminDao.getAllAdmin();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	//*****************************************************************
	
	public AdminIdao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminIdao adminDao) {
		this.adminDao = adminDao;
	}

	//*****************************************************************
}
